package org.numisoft.webproject.dto;

public class Subscription {

	private int id;
	private int user_id;
	private int periodical_id;
	private String title;
	private String link;
	private String country;

	public Subscription() {
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPeriodical_id() {
		return periodical_id;
	}

	public void setPeriodical_id(int periodical_id) {
		this.periodical_id = periodical_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
