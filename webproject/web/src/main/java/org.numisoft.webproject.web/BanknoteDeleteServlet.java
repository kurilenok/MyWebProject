package org.numisoft.webproject.web;

import org.apache.log4j.Logger;
import org.numisoft.webproject.services.BanknoteService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BanknoteDeleteServlet deletes Banknotes from Catalog
 * BanknoteDeleteServlet can be used only by Admin
 *
 */

public class BanknoteDeleteServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Logger logger = Logger.getLogger(BanknoteDeleteServlet.class);

        int id = Integer.parseInt(request.getParameter("id"));

        BanknoteService banknoteService = BanknoteService.getInstance();
        banknoteService.removeBanknoteFromCatalog(id);

        logger.debug("<<@>> Admin deleted Banknote: id=" + id);

        response.sendRedirect("/webproject/index");

    }

}
