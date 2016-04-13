package org.numisoft.webproject.web;

import org.apache.log4j.Logger;
import org.numisoft.webproject.services.CollectibleService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

		int id = Integer.parseInt(request.getParameter("id"));


		CollectibleService collectibleService = CollectibleService.getInstance();
		collectibleService.deleteCollectible(id);

		logger.debug("<<@>> User deleted Collectible: id=" + id);

		response.sendRedirect("/webproject/index");

	}
}
