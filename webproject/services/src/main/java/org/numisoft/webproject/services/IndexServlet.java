package org.numisoft.webproject.services;

import org.numisoft.webproject.dao.BanknoteDaoImpl;
import org.numisoft.webproject.dao.CollectibleDaoImpl;
import org.numisoft.webproject.dto.Banknote;
import org.numisoft.webproject.dto.Collectible;
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

        BanknoteDaoImpl bdi = BanknoteDaoImpl.getInstance();
        List<Banknote> banknotes = bdi.getAllBanknotes();

        request.setAttribute("banknotes", banknotes);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.setAttribute("user_id", user.getId());

        if (user.getRole_id() == 1) {

            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);

        } else {

            CollectibleDaoImpl cdi = CollectibleDaoImpl.getInstance();
            List<Collectible> collectibles = cdi.getCollectiblesByUser(user);
            request.setAttribute("collectibles", collectibles);

            RequestDispatcher dispatcher = request.getRequestDispatcher("collectibles.jsp");
            dispatcher.forward(request, response);
        }

    }
}
