package org.numisoft.webproject.web;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.numisoft.webproject.services.UserService;
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

		UserService userService = UserService.getInstance();

		int id = userService.authenticate(username, password);

		if (id > 0) {

			HttpSession session = request.getSession(true);
			session.setAttribute("user", userService.getUserById(id));
			session.setAttribute("currentPage", 1);

			logger.debug("<<@>> User logged in: " + userService.getUserById(id).getUsername());

			response.sendRedirect("/webproject/index");

		} else {
			response.sendRedirect("/webproject/error.jsp");
		}

	}
}
