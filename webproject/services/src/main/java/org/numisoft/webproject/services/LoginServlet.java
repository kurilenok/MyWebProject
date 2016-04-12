package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.UserDaoImpl;
import org.numisoft.webproject.dto.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserDaoImpl udi = UserDaoImpl.getInstance();

		int id = udi.authenticate(username, password);

		if (id > 0) {

			User user = udi.getUserById(id);

			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			response.sendRedirect("/webproject/index");

		} else {
			response.sendRedirect("/webproject/error.jsp");
		}

	}
}
