package org.numisoft.webproject.dto;

public class Subscription {

	private int id;
	private String date;
	private int user_id;
	private String first_name;
	private String last_name;
	private int periodical_id;
	private String title;

	public Subscription() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}
