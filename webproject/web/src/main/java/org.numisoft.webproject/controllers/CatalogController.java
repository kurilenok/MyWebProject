package org.numisoft.webproject.controllers;

import org.apache.log4j.Logger;
import org.numisoft.webproject.dto.BanknoteDto;
import org.numisoft.webproject.services.BanknoteService;
import org.numisoft.webproject.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kukolka on 26.05.16.
 */

/* This Controller is responsible to Catalog */
@Controller
public class CatalogController {

    Logger logger = Logger.getLogger(CatalogController.class);

    @Autowired
    BanknoteService banknoteService;

    @Autowired
    BanknoteDto banknoteDto;

    /* Catalog set-up */
    @RequestMapping(value = "/catalog")
    public String showCatalog(ModelMap modelMap,
                              @RequestParam(value = "page", defaultValue = "1") Integer page) {

        modelMap.put("banknotes", banknoteService.getAllBanknotes(page));
        modelMap.put("currentPage", page);
        modelMap.put("maxPages", banknoteService.calculateMaxPages());
        modelMap.put("banknoteDto", banknoteDto);

        return "catalog";
    }

    /* Admin deletes banknote from Catalog */
    @RequestMapping(value = "/catalog/delete")
    public String removeFromCatalog(@RequestParam(value = "id", defaultValue = "0") Integer banknote_id) {

        if (banknote_id == 0) {
            return Constants.REDIRECT_TO_400_ERROR;
        }

        banknoteService.removeBanknoteFromCatalog(banknote_id);

        logger.debug("<<@>> Admin deleted banknote: id=" + banknote_id);

        return Constants.REDIRECT_TO_CATALOG;
    }

    /* Admin adds new banknote to Catalog */
    @RequestMapping(value = "/catalog/add")
    public String addToCatalog(@ModelAttribute BanknoteDto banknoteDto) {

        if (banknoteDto == null) {
            return Constants.REDIRECT_TO_400_ERROR;
        }

        String title = banknoteDto.getTitle();
        int nominal = banknoteDto.getNominal();
        String country = banknoteDto.getCountry();
        String link = banknoteDto.getLink();

        banknoteService.addBanknoteToCatalog(title, nominal, country, link);

        logger.debug("<<@>> Admin added banknote: " + nominal + " " + title);

        return Constants.REDIRECT_TO_CATALOG;
    }


}
