package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Periodical;

import java.util.List;

public interface PeriodicalDao {

	Periodical getPeriodicalByid(int id);

	List<Periodical> getAllPeriodicals();

	void addPeriodical(String title, String country, String link);

	void deletePeriodical(int id);

}
