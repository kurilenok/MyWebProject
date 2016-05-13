package org.numisoft.webproject.web;

import org.apache.log4j.Logger;
import org.numisoft.webproject.services.BanknoteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DeleteFromCatalogServlet deletes Banknotes from Catalog
 * DeleteFromCatalogServlet can be used only by Admin
 *
 */

public class DeleteFromCatalogServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Logger logger = Logger.getLogger(DeleteFromCatalogServlet.class);

        int id = Integer.parseInt(request.getParameter("id"));

        BanknoteServiceImpl banknoteServiceImpl = BanknoteServiceImpl.getInstance();
        banknoteServiceImpl.removeBanknoteFromCatalog(id);

        logger.debug("<<@>> Admin deleted Banknote: id=" + id);

        response.sendRedirect(Constants.PATH_TO_INDEX);

    }

}
