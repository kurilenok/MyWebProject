package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.PeriodicalDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		PeriodicalDaoImpl pdi = new PeriodicalDaoImpl();
		pdi.deletePeriodical(id);

		response.sendRedirect("/webproject/index");

	}

}
