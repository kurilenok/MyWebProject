package org.numisoft.webproject.web;

import org.numisoft.webproject.dto.User;
import org.numisoft.webproject.services.BanknoteService;
import org.numisoft.webproject.services.UserService;

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
 */


public class IndexServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer page = -1;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {

        }

        if (page == null || page < 0) {
            page = 1;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.setAttribute("user_id", user.getId());

        BanknoteService banknoteService = BanknoteService.getInstance();
        request.setAttribute("banknotes", banknoteService.getAllBanknotes(page));
        request.setAttribute("currentPage", page);
        request.setAttribute("maxPages", banknoteService.calculateMaxPages());

        if (user.getRole_id() == 1) {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/catalog.jsp");
            dispatcher.forward(request, response);

        } else {

            UserService userService = UserService.getInstance();
            request.setAttribute("collectibles", userService.getUserCollection(user.getId()));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/collection.jsp");
            dispatcher.forward(request, response);
        }

    }
}
