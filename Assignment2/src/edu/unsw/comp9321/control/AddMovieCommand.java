package edu.unsw.comp9321.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringEscapeUtils;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.model.Movie;

public class AddMovieCommand implements Command {
	private static final String DATA_DIRECTORY = "img";
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024;

	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Logger logger = Logger.getLogger(this.getClass().getName());
		// salvar url

		Part filePart = request.getPart("poster"); // Retrieves <input type="file" name="file">
	    String fileName = getFilename(filePart);
	    InputStream filecontent = filePart.getInputStream();
	    byte[] buffer = new byte[filecontent.available()];
	    filecontent.read(buffer);

	    String filePath = request.getServletContext().getRealPath("") + File.separator
	    		+ "img" + File.separator;
	    File targetFile = new File(filePath + fileName);
	    logger.info("File " + fileName + " created at " + filePath);
	    OutputStream outStream = new FileOutputStream(targetFile);
	    outStream.write(buffer);

		/*
		 * Part image = request.getPart("image"); String destPath = "/img/";
		 * OutputStream out = null; InputStream imagecontent = null;
		 * 
		 * try { out = new FileOutputStream(new File("testel.ra")); //criou, mas
		 * diretorio errado imagecontent = image.getInputStream();
		 * 
		 * int read = 0; byte[] bytes = new byte[1024];
		 * 
		 * while ((read = imagecontent.read(bytes)) != -1) { out.write(bytes, 0,
		 * read); } logger.info("New file " + movie.getTitle() + " created at "
		 * + destPath); } catch (FileNotFoundException e) {
		 * logger.warning("Problems during file upload. Error:" +
		 * e.getMessage()); } finally { if (out != null) out.close(); if
		 * (imagecontent != null) imagecontent.close();
		 * 
		 * }
		 * 
		 * //ImageIO.write((RenderedImage) image, "png", new
		 * File("imagem.png"));
		 */

		Movie movie = new Movie();

		movie.setTitle(request.getParameter("title"));
		movie.setGenre(request.getParameter("genre"));
		movie.setDirector(request.getParameter("director"));
		movie.setSynopsis(request.getParameter("synopsis"));
		movie.setAgeRating(Integer.parseInt(request.getParameter("ageRating")));
		movie.setUrlPost(fileName);
		movie.setRating(0);

		// Manipulating date
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = dateFormat.parse(request.getParameter("releaseDate"));
			movie.setReleaseDate(date);
		} catch (ParseException e) {
			logger.warning("Problem reading the release date of the movie: "
					+ e.getStackTrace());
		}
		
		try {
			MovieDAO dao = new HibernateMovieDAO();
			dao.addMovie(movie);

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request
				.getRequestDispatcher("control?action=toHomePage");
		rd.forward(request, response);

	}

}
