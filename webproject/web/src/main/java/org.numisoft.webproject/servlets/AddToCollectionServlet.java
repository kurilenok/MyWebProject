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
 * Servlet adds Banknote to User Collectiion
 * Banknote added to Collection becomes Collectible
 * CSerlvet is used by any authenticated User (except Admin)
 *
 */

public class AddToCollectionServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Logger logger = Logger.getLogger(AddToCollectionServlet.class);

		int banknote_id = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession(false);
		int user_id = (Integer) session.getAttribute("user_id");

		UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
		userServiceImpl.addBanknoteToCollection(user_id, banknote_id);

		logger.debug("<<@>> User added Collectible: id=" + banknote_id);

		response.sendRedirect(Constants.PATH_TO_INDEX);

	}

}
