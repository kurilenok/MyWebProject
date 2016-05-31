package org.numisoft.webproject.controllers;

import org.numisoft.webproject.pojos.User;
import org.numisoft.webproject.services.BanknoteService;
import org.numisoft.webproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String index(ModelMap modelMap) {
        modelMap.put("user", user);
        return "forward:/sortUser";
    }


    @RequestMapping(value = "/sortUser")
    public String sortUser(ModelMap modelMap, HttpSession httpSession) {

        modelMap.get("user");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user = userService.getUserByName(auth.getName());

        if (user == null) {
            return "login";
        }

        if (user.getRole().equalsIgnoreCase("ROLE_USER")) {
            httpSession.setAttribute("user", user);
            return "redirect:/collection";
        } else if (user.getRole().equalsIgnoreCase("ROLE_ADMIN")){
            return "redirect:/catalog";
        } else return "error";


    }

    @RequestMapping(value = "/login2")
//    @RequestMapping(value = "/login")
    public String checkLogin(ModelMap modelMap, @ModelAttribute User user, HttpSession httpSession) {

        modelMap.get("user");

        String username = ((User) modelMap.get("user")).getUsername();
        String password = ((User) modelMap.get("user")).getPassword();

        int id = userService.authenticate(username, password);

        if (id < 0) {
            return "error2";
        }

        user = userService.getUserById(id);

        if (user.getRole_id() == 1) {
            /* User == ADMIN */
            return "redirect:/catalog";
        } else {
            /*User != ADMIN */
            httpSession.setAttribute("user", user);
            return "redirect:/collection";
        }
    }

//    @RequestMapping("/logout")
//    public String logout(ModelMap modelMap, HttpSession httpSession) {
//        httpSession.invalidate();
//        modelMap.put("user", user);
//        return "index";
//    }

}
