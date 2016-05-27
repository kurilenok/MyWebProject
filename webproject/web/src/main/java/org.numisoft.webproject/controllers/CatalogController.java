package org.numisoft.webproject.controllers;

import org.numisoft.webproject.dto.BanknoteDto;
import org.numisoft.webproject.services.BanknoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kukolka on 26.05.16.
 */

@Controller
public class CatalogController {

    @Autowired
    BanknoteService banknoteService;

    @Autowired
    BanknoteDto banknoteDto;

    @RequestMapping("/catalog")
    public String showCatalog(ModelMap modelMap,
                              @RequestParam(value = "page", defaultValue = "1") Integer page) {

        modelMap.put("banknotes", banknoteService.getAllBanknotes(page));
        modelMap.put("currentPage", page);
        modelMap.put("maxPages", banknoteService.calculateMaxPages());

        modelMap.put("banknoteDto", banknoteDto);

        return "catalog";
    }

    @RequestMapping("/delete")
    public String removeFromCatalog(ModelMap modelMap,
                                    @RequestParam(value = "id", defaultValue = "0") Integer banknote_id) {

        banknoteService.removeBanknoteFromCatalog(banknote_id);
        return "forward:/catalog";
    }


    @RequestMapping("/add")
    public String addToCatalog(ModelMap modelMap, @ModelAttribute BanknoteDto banknoteDto) {

        String title = ((BanknoteDto) modelMap.get("banknoteDto")).getTitle();
        int nominal = banknoteDto.getNominal();
        String country = banknoteDto.getCountry();
        String link = banknoteDto.getLink();

        banknoteService.addBanknoteToCatalog(title, nominal, country, link);

        return "forward:/catalog";
    }


}
