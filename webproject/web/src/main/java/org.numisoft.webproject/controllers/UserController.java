package org.numisoft.webproject.controllers;

import org.numisoft.webproject.pojos.User;
import org.numisoft.webproject.services.BanknoteService;
import org.numisoft.webproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    User user;

    @Autowired
    UserService userService;

    @Autowired
    BanknoteService banknoteService;


    @RequestMapping(value = "/")
    public String index() {
        return "forward:/sortUser";
    }


    @RequestMapping(value = "/sortUser")
    public String sortUser(HttpSession httpSession) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user = userService.getUserByName(auth.getName());

        if (user == null) {
            return "redirect:/login.jsp";
        }

        if (user.getRole().equalsIgnoreCase("ROLE_USER")) {
            httpSession.setAttribute("user", user);
            return "redirect:/collection";
        } else if (user.getRole().equalsIgnoreCase("ROLE_ADMIN")){
            return "redirect:/catalog";
        } else return "error";

    }

}
