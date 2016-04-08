package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Periodical;
import org.numisoft.webproject.utils.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalDaoImpl implements PeriodicalDao {

//	@Override
	public Periodical getPeriodicalByid(int id) {
		return null;
	}

//	@Override
	public List<Periodical> getAllPeriodicals() {

		List<Periodical> periodicals = new ArrayList<Periodical>();

		try {
			String template = "select * from periodicals;";

			Connection connection = DataSource.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(template);

			while (result.next()) {
				Periodical periodical = new Periodical();
				periodical.setId(result.getInt("id"));
				periodical.setTitle(result.getString("title"));
				periodical.setPrice(result.getInt("price"));
				periodical.setLink(result.getString("link"));
				periodicals.add(periodical);
			}

			result.close();
			statement.close();
			connection.close();

		} catch (SQLException|IOException|PropertyVetoException e) {
			e.printStackTrace();
		}

		return periodicals;
	}



	public void addPeriodical(String title, int price, String link) {

		try {
			String template = "insert into periodicals (title, price, link) values ('" + title + "',"
					+ price + ", '" + link + "');";

			Connection connection = DataSource.getInstance().getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(template);

			statement.close();
			connection.close();

		} catch (SQLException|IOException|PropertyVetoException e) {
			e.printStackTrace();
		}

	}


	public void deletePeriodical(int id) {

		try {
			String template = "delete from periodicals where id=" + id + ";";

			Connection connection = DataSource.getInstance().getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(template);

			statement.close();
			connection.close();

		} catch (SQLException|IOException|PropertyVetoException e) {
			e.printStackTrace();
		}

	}

}
