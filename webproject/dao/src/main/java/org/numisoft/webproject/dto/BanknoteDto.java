package org.numisoft.webproject.dto;

import org.springframework.stereotype.Component;

/**
 * Created by kukolka on 27.05.16.
 */
@Component
public class BanknoteDto {

    private String country;
    private int nominal;
    private String title;
    private String link;

    public BanknoteDto() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
