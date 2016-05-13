package org.numisoft.webproject.dto;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * User..
 */

@Entity
@Table(name = "users")
@Proxy(lazy = false)

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String first_name;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String last_name;

    @Column
    private int role_id;

    @ManyToMany(fetch = FetchType.EAGER,
            targetEntity = org.numisoft.webproject.dto.Banknote.class)
    @JoinTable(name = "collections",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "banknote_id")})
    private Set<Banknote> banknotes = new TreeSet<>();

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

  public Set<Banknote> getBanknotes() {
        return banknotes;
    }

    public void setBanknotes(Set<Banknote> banknotes) {
        this.banknotes = banknotes;
    }
}
