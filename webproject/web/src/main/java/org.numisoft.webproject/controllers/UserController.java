package org.numisoft.webproject.controllers;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.numisoft.webproject.pojos.User;
import org.numisoft.webproject.services.BanknoteService;
import org.numisoft.webproject.services.UserService;
import org.numisoft.webproject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
//        return "catalog";
        return "index";
    }

    @RequestMapping(value = "/login")
    public String checkLogin(ModelMap modelMap, @ModelAttribute User user, HttpSession httpSession) {

        modelMap.get("user");

        String username = ((User) modelMap.get("user")).getUsername();
        String password = ((User) modelMap.get("user")).getPassword();

        int id = userService.authenticate(username, password);

        if (id < 0) {
            return "error";
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

    @RequestMapping("/logout")
    public String logout(ModelMap modelMap, HttpSession httpSession) {
        httpSession.invalidate();
        modelMap.put("user", user);
        return "index";
    }

}
