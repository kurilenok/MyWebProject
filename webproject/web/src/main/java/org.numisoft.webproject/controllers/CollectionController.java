package org.numisoft.webproject.controllers;

import org.numisoft.webproject.pojos.Banknote;
import org.numisoft.webproject.pojos.User;
import org.numisoft.webproject.services.BanknoteService;
import org.numisoft.webproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Created by kukolka on 26.05.16.
 */

@Controller
public class CollectionController {

    @Autowired
    UserService userService;

    @Autowired
    BanknoteService banknoteService;

    @Autowired
    User user;

    @RequestMapping("/collection")
    public String showCollection(ModelMap modelMap,
                                 @RequestParam(value = "page", defaultValue = "1") int page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user = userService.getUserByName(auth.getName());

        if (user == null) {
            return "redirect:/login.jsp";
        }

        int user_id = user.getId();

        Set<Banknote> collection = userService.getUserCollection(user_id);
        modelMap.put("collection", collection);
        modelMap.put("banknotes", banknoteService.getAllBanknotes(page));
        modelMap.put("currentPage", page);
        modelMap.put("maxPages", banknoteService.calculateMaxPages());

        return "collection";
    }

    @RequestMapping("/collect")
    public String collect(HttpSession httpSession,
                          @RequestParam(value = "id", defaultValue = "0") int banknote_id) {

        int user_id = ((User) httpSession.getAttribute("user")).getId();
        userService.addBanknoteToCollection(user_id, banknote_id);

        return "redirect:/collection";
    }

    @RequestMapping("/uncollect")
    public String uncollect(HttpSession httpSession,
                            @RequestParam(value = "id", defaultValue = "0") int banknote_id) {

        int user_id = ((User) httpSession.getAttribute("user")).getId();
        userService.removeBanknoteFromCollection(user_id, banknote_id);

        return "redirect:/collection";
    }


}
