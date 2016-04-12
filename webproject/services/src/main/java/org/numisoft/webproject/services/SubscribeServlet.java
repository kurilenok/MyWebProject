package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.CollectibleDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SubscribeServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession(false);
		int user_id = (Integer) session.getAttribute("user_id");

		CollectibleDaoImpl sdi = CollectibleDaoImpl.getInstance();
		sdi.addCollectible(user_id, id);

		response.sendRedirect("/webproject/index");

	}

}
