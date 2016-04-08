package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.PeriodicalDaoImpl;
import org.numisoft.webproject.dao.SubscriptionDaoImpl;
import org.numisoft.webproject.dto.Periodical;
import org.numisoft.webproject.dto.Subscription;
import org.numisoft.webproject.dto.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PeriodicalDaoImpl pdi = new PeriodicalDaoImpl();
		List<Periodical> ps = pdi.getAllPeriodicals();
		request.setAttribute("periodicals", ps);

		User user = new User();

		HttpSession session = request.getSession();
		user = (User) session.getAttribute("user");
		session.setAttribute("user_id", user.getId());

		if (user.getRole_id() == 1) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
			dispatcher.forward(request, response);

		} else {

			SubscriptionDaoImpl sdi = new SubscriptionDaoImpl();
			List<Subscription> subscriptions = sdi.getSubscriptionByUser(user);
			request.setAttribute("subscriptions", subscriptions);

			RequestDispatcher dispatcher = request.getRequestDispatcher("subscriptions.jsp");
			dispatcher.forward(request, response);
		}

	}
}
