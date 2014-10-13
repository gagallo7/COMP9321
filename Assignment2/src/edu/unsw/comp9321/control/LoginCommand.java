package edu.unsw.comp9321.control;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.ObjectNotFoundException;

import edu.unsw.comp9321.dao.HibernateMovieDAO;
import edu.unsw.comp9321.dao.MovieDAO;
import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.model.UserLogin;
import edu.unsw.comp9321.util.OwaspHashUtil;
import edu.unsw.comp9321.util.SaferStringUtil;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = SaferStringUtil.convert(request.getParameter("username"));
		String password = request.getParameter("password");
		String nextPage = "control?action=toHomePage";
		
		logger.info("Username = " + username);
		logger.info("Password = " + password);
		
		
		if (!username.isEmpty()) {
			HttpSession session = request.getSession();
			if (username.equals("admin") && password.equals("123")) {
				session.setAttribute("UserRole", "manager");
			} else {

				try {
					MovieDAO dao = new HibernateMovieDAO();
					UserLogin user = dao.getUser(username);	
					
					if(user == null){
						request.getSession().setAttribute("info", "Login");
						request.getSession().setAttribute("moreInfo", "Invalid Username!");
						nextPage = "fail.jsp";	
					}
					
					
					dao.closeSession();
				} catch (ServiceLocatorException e) {
					e.printStackTrace();
				}	
			}
		}
					
					
					
					
				/*
					
	
						
					}else{
						byte[] bSalt = OwaspHashUtil.base64ToByte(user.getCode());
						byte[] bDigest = OwaspHashUtil.getHash(
								OwaspHashUtil.ITERATION_NUMBER, password, bSalt);
						String possiblePassword = OwaspHashUtil.byteToBase64(bDigest);
						logger.info("HASH" + possiblePassword);
						if (possiblePassword == user.getPassword()){	
							session.setAttribute("UserRole", "user");
							session.setAttribute("username", username);
							session.setAttribute("nickname", user.getNickname());
						}else{
							request.getSession().setAttribute("info", "Logn");
							request.getSession().setAttribute("moreInfo", "Invalid Password!");
							nextPage = "fail.jsp";						
						}
					}
					
					dao.closeSession();	
				} catch (ServiceLocatorException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		}
		*/
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
