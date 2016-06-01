package org.numisoft.webproject.controllers;

import org.apache.log4j.Logger;
import org.numisoft.webproject.pojos.Banknote;
import org.numisoft.webproject.pojos.User;
import org.numisoft.webproject.services.BanknoteService;
import org.numisoft.webproject.services.UserService;
import org.numisoft.webproject.utils.Constants;
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

    Logger logger = Logger.getLogger(CollectionController.class);

    @Autowired
    UserService userService;

    @Autowired
    BanknoteService banknoteService;

    @Autowired
    User user;

    /* User Collection set-up */
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

    /* User adds banknote to his Collection */
    @RequestMapping("/collection/collect")
    public String collect(@RequestParam(value = "id", defaultValue = "0") int banknote_id) {

        if (banknote_id == 0) {
            return Constants.REDIRECT_TO_400_ERROR;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user = userService.getUserByName(auth.getName());

        int user_id = user.getId();
        userService.addBanknoteToCollection(user_id, banknote_id);

        logger.debug("<<@>> User " + user.getUsername() + " added banknote id=" + banknote_id );

        return Constants.REDIRECT_TO_COLLECTION;
    }

    /* User removes banknote from his Collection */
    @RequestMapping("/collection/uncollect")
    public String uncollect(@RequestParam(value = "id", defaultValue = "0") int banknote_id) {

        if (banknote_id == 0) {
            return Constants.REDIRECT_TO_400_ERROR;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user = userService.getUserByName(auth.getName());

        int user_id = user.getId();
        userService.removeBanknoteFromCollection(user_id, banknote_id);

        logger.debug("<<@>> User " + user.getUsername() + " removed banknote id=" + banknote_id );

        return Constants.REDIRECT_TO_COLLECTION;
    }


}
