package org.numisoft.webproject.services;


import org.numisoft.webproject.dao.PeriodicalDaoImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String title = request.getParameter("title");
		String priceString = request.getParameter("price");
		String link = request.getParameter("link");

		if (title.equalsIgnoreCase("")) {
			response.sendRedirect("/webproject/index");
		} else if (priceString.equalsIgnoreCase("")) {
			response.sendRedirect("/webproject/index");
		} else {

			int price = 0;
			try {
				price = Integer.parseInt(priceString);
			} catch (NumberFormatException e) {

			}
			PeriodicalDaoImpl pdi = new PeriodicalDaoImpl();
			pdi.addPeriodical(title, price, link);

			response.sendRedirect("/webproject/index");
		}

	}
}
