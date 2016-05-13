package org.numisoft.webproject.web;

import org.numisoft.webproject.dto.User;
import org.numisoft.webproject.services.BanknoteServiceImpl;
import org.numisoft.webproject.services.UserServiceImpl;

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

        BanknoteServiceImpl banknoteServiceImpl = BanknoteServiceImpl.getInstance();
        request.setAttribute("banknotes", banknoteServiceImpl.getAllBanknotes(page));
        request.setAttribute("currentPage", page);
        request.setAttribute("maxPages", banknoteServiceImpl.calculateMaxPages());

        if (user.getRole_id() == 1) {

            RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.PATH_TO_CATALOG);
            dispatcher.forward(request, response);

        } else {

            UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
            request.setAttribute("collectibles", userServiceImpl.getUserCollection(user.getId()));

            RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.PATH_TO_COLLECTION);
            dispatcher.forward(request, response);
        }

    }
}
