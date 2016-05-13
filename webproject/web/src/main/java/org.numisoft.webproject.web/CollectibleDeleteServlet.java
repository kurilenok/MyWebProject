package org.numisoft.webproject.web;

import org.apache.log4j.Logger;
import org.numisoft.webproject.services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * CollectibleDeleteSerlvet deletes Banknote to User Collectiion
 * CollectibleDeleteSerlvet can be used by any authenticated User (except Admin)
 *
 */

public class CollectibleDeleteServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Logger logger = Logger.getLogger(CollectibleDeleteServlet.class);

		int banknote_id = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession(false);
		int user_id = (Integer) session.getAttribute("user_id");

		UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
		userServiceImpl.removeBanknoteFromCollection(user_id, banknote_id);

		logger.debug("<<@>> User deleted Collectible: id=" + banknote_id);

		response.sendRedirect(Constants.PATH_TO_INDEX);

	}
}
