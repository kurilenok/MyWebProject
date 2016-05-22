package org.numisoft.webproject.web.servlets;


import org.apache.log4j.Logger;
import org.numisoft.webproject.services.BanknoteServiceImpl;
import org.numisoft.webproject.web.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AddToCatalogServlet adds Banknotes to Catalog
 * AddToCatalogServlet can be used only by Admin
 *
 */

public class AddToCatalogServlet extends HttpServlet {

    Logger logger = Logger.getLogger(AddToCatalogServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        int nominal = Integer.parseInt(request.getParameter("nominal"));
        String country = request.getParameter("country");
        String link = request.getParameter("link");

        if ("".equals(title)) {
            response.sendRedirect(Constants.PATH_TO_INDEX);
        } else if (country.equalsIgnoreCase("")) {
            response.sendRedirect(Constants.PATH_TO_INDEX);
        } else if (link.equalsIgnoreCase("")) {
            response.sendRedirect(Constants.PATH_TO_INDEX);
        } else {

            BanknoteServiceImpl banknoteServiceImpl = BanknoteServiceImpl.getInstance();
            banknoteServiceImpl.addBanknoteToCatalog(title, nominal, country, link);

            logger.debug("<<@>> Admin added Banknote title:" + title);

            response.sendRedirect(Constants.PATH_TO_INDEX);
        }

    }
}
