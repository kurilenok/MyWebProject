package org.numisoft.webproject.controllers;

import org.apache.log4j.Logger;
import org.numisoft.webproject.pojos.User;
import org.numisoft.webproject.services.BanknoteService;
import org.numisoft.webproject.services.UserService;
import org.numisoft.webproject.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/* This Controller is responsible for User role check */
@Controller
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    User user;

    @Autowired
    UserService userService;

    @Autowired
    BanknoteService banknoteService;

    /* Application entry point */
    @RequestMapping(value = "/")
    public String index() {
        return "forward:/sortUser";
    }


    /* Check if User role and redirect accordingly: */
    /* User --> Collection || Admin --> Catalog */
    @RequestMapping(value = "/sortUser")
    public String sortUser(HttpSession httpSession) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user = userService.getUserByName(auth.getName());

        if (user == null) {
            return "forward:/login.jsp";
        }

        if (user.getRole().equalsIgnoreCase("ROLE_USER")) {

            httpSession.setAttribute("user", user);
            logger.debug("<<@>> User " + user.getUsername() + " logged in");

            return Constants.REDIRECT_TO_COLLECTION;

        } else if (user.getRole().equalsIgnoreCase("ROLE_ADMIN")) {

            logger.debug("<<@>> Admin logged in");

            return Constants.REDIRECT_TO_CATALOG;

        } else return "redirect:/401.jsp";


    }



}
