package org.numisoft.webproject.dto;

import java.util.Set;

/**
 * User..
 *
 * */


public class User {

	private int id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private int role_id;

	private Set<Banknote> banknotes;

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
