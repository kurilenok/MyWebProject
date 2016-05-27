package org.numisoft.webproject.pojos;

import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Banknote = item in General Catalog
 */

@Entity
@Table(name = "banknotes")
@Proxy(lazy = false)
@Component
public class Banknote implements Serializable, Comparable<Banknote> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Country country;

    @Column
    private int nominal;

    @Column
    private String title;

    @Column
    private String link;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "collections",
            joinColumns = {@JoinColumn(name = "banknote_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
	private Set<User> users = new TreeSet<>();

    public Banknote() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country.getCountryName();
    }

    public void setCountry(Country country) {
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


    @Override
    public int compareTo(Banknote b2) {
        if (getCountry().equalsIgnoreCase(b2.getCountry())) {
            if (getNominal() > b2.getNominal()) {
                return 1;
            } else return -1;
        } else
            return getCountry().compareToIgnoreCase(b2.getCountry());

    }

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
