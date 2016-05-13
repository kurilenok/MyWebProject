package org.numisoft.webproject.web;


import org.apache.log4j.Logger;
import org.numisoft.webproject.services.BanknoteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BanknoteAddServlet adds Banknotes to Catalog
 * BanknoteAddServlet can be used only by Admin
 *
 */

public class BanknoteAddServlet extends HttpServlet {

    Logger logger = Logger.getLogger(BanknoteAddServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        int nominal = Integer.parseInt(request.getParameter("nominal"));
        String country = request.getParameter("country");
        String link = request.getParameter("link");

        if (title.equalsIgnoreCase("")) {
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