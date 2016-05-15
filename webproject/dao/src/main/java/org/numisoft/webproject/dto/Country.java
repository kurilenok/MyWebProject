package org.numisoft.webproject.dto;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kukolka on 11.05.16.
 */

@Entity
@Table(name = "countries")
@Proxy(lazy = false)
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OrderColumn
    @Column (name = "country")
    private String countryName;


    public Country() {
    }

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String name) {
        this.countryName = name;
    }
}
