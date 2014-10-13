package edu.unsw.comp9321.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostRedirectGetUtil {
	public static void postRedirectGet(HttpServletRequest request,
			HttpServletResponse response, String Target) {
		/* Post-Redirect-Get implementation */
		URL url;
		try {
			url = new URL(request.getScheme(), request.getServerName(),
					request.getServerPort(), request.getContextPath());

//			logger.info("Post redirecting to " + url.toExternalForm());

			response.sendRedirect(url.toExternalForm() + "/" + Target);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
