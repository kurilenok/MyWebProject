package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Banknote;

import java.util.List;

public interface BanknoteDao {

	Banknote getBanknoteByid(int id);

	List<Banknote> getAllBanknotes();

	void addBanknote(String title, String country, String link);

	void deleteBanknote(int id);

}
