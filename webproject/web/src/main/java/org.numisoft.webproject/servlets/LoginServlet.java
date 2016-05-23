package org.numisoft.webproject.servlets;

import org.apache.log4j.Logger;
import org.numisoft.webproject.services.UserServiceImpl;
import org.numisoft.webproject.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LoginServlet authenticates User
 *
 * */

public class LoginServlet extends HttpServlet {
	Logger logger = Logger.getLogger(LoginServlet.class);

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

		int id = userServiceImpl.authenticate(username, password);

		if (id > 0) {

			HttpSession session = request.getSession(true);
			session.setAttribute("user", userServiceImpl.getUserById(id));
			session.setAttribute("currentPage", 1);

			logger.debug("<<@>> User logged in: " + userServiceImpl.getUserById(id).getUsername());

			response.sendRedirect(Constants.PATH_TO_INDEX);

		} else {
			response.sendRedirect("/webproject/error.jsp");
		}

	}
}
