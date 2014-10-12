package edu.unsw.comp9321.control;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.fileupload.FileItem;
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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Logger logger = Logger.getLogger(this.getClass().getName());
		// salvar url

		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (!isMultipart) {
			return;
		}

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Sets the size threshold beyond which files are written directly to
		// disk.
		factory.setSizeThreshold(MAX_MEMORY_SIZE);

		// Sets the directory used to temporarily store files that are larger
		// than the configured size threshold. We use temporary directory for
		// java
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// constructs the folder where uploaded file will be stored
		// String uploadFolder = getServletContext().getRealPath("")
		// + File.separator + DATA_DIRECTORY;
		String uploadFolder = request.getAttribute("path") + File.separator + DATA_DIRECTORY;

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		try {
			// Parse the request
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			logger.info("Trying to save Poster3" + iter.toString() + " has next " + iter.hasNext());
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				
				if (!item.isFormField()) {
					String fileName = new File(item.getName()).getName();
					logger.info("File name? " + fileName);
					String filePath = uploadFolder + File.separator + fileName;
					logger.info("File path? " + filePath);
					File uploadedFile = new File(filePath);
					System.out.println(filePath);
					// saves the file to upload directory
					item.write(uploadedFile);
					logger.info("Poster saved in: " + filePath);
				}
			}

		} catch (FileUploadException ex) {
			throw new ServletException(ex);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

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

//		Movie movie = new Movie();
//
//		movie.setTitle(request.getParameter("title"));
//		movie.setGenre(request.getParameter("genre"));
//		movie.setDirector(request.getParameter("director"));
//		movie.setSynopsis(request.getParameter("synopsis"));
//		movie.setAgeRating(Integer.parseInt(request.getParameter("ageRating")));
//		movie.setRating(0);
//
//		// Manipulating date
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		Date date;
//		try {
//			date = dateFormat.parse(request.getParameter("releaseDate"));
//			movie.setReleaseDate(date);
//		} catch (ParseException e) {
//			logger.warning("Problem reading the release date of the movie: "
//					+ e.getStackTrace());
//		}
//		
//		try {
//			MovieDAO dao = new HibernateMovieDAO();
//			dao.addMovie(movie);
//
//		} catch (ServiceLocatorException e) {
//			e.printStackTrace();
//		}

		RequestDispatcher rd = request
				.getRequestDispatcher("control?action=toHomePage");
		rd.forward(request, response);

	}

}
