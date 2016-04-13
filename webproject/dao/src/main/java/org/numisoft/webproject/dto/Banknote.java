package org.numisoft.webproject.dto;

/**
 *
 * Banknote = item in General Catalog
 *
 * */


public class Banknote {

	private int id;
	private String country;
	private String title;
	private String link;
	public Banknote() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
