package org.numisoft.webproject.web;

import org.numisoft.webproject.dto.User;
import org.numisoft.webproject.services.BanknoteService;
import org.numisoft.webproject.services.CollectibleService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * IndexServlet sorts Users (Admin or non-Admin User)
 * and redirects Admin to catalog.jsp or non-Admin to collectible.jsp
 *
 * */


public class IndexServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BanknoteService banknoteService = BanknoteService.getInstance();
        request.setAttribute("banknotes", banknoteService.getAllBanknotes());

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.setAttribute("user_id", user.getId());

        if (user.getRole_id() == 1) {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/catalog.jsp");
            dispatcher.forward(request, response);

        } else {

            CollectibleService collectibleService = CollectibleService.getInstance();
            request.setAttribute("collectibles", collectibleService.getCollectiblesByUser(user));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/collection.jsp");
            dispatcher.forward(request, response);
        }

    }
}
