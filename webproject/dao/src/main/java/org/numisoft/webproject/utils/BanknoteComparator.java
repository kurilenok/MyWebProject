package org.numisoft.webproject.utils;

import org.numisoft.webproject.dto.Banknote;

import java.util.Comparator;

/**
 * This Comparator sorts Banknotes in TreeSet
 * First by Country name, second by Nominal value
 */
public class BanknoteComparator implements Comparator<Banknote> {
    @Override
    public int compare(Banknote b1, Banknote b2) {
        if (b1.getCountry().equalsIgnoreCase(b2.getCountry())) {
            if (b1.getNominal() > b2.getNominal()) {
                return 1;
            } else return -1;
        } else
            return b1.getCountry().compareToIgnoreCase(b2.getCountry());

    }
}