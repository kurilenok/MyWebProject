package org.numisoft.webproject.servlets;



import org.apache.log4j.Logger;
import org.numisoft.webproject.pojos.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LogoutServlet invalidates User Session
 *
 * */

public class LogoutServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Logger logger = Logger.getLogger(LogoutServlet.class);

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		logger.debug("<<@>> User logged out: " + user.getUsername());

		session.invalidate();

		response.sendRedirect("/webproject");

	}

}
