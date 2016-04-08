package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Subscription;
import org.numisoft.webproject.dto.User;
import org.numisoft.webproject.utils.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {

	public List<Subscription> getSubscriptionByUser(User user) {

		List<Subscription> subscriptions = new ArrayList<Subscription>();

		String template = "select subscriptions.id, subscriptions.date, "
				+ "users.first_name, users.last_name, periodicals.title " + "from subscriptions "
				+ "join periodicals on subscriptions.periodical_id=periodicals.id "
				+ "join users on subscriptions.user_id=users.id where users.last_name='"
				+ user.getLast_name() + "';";

		try {
			Connection connection = DataSource.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(template);

			while (result.next()) {
				Subscription subscription = new Subscription();
				subscription.setId(result.getInt("id"));
				subscription.setDate(result.getString("date"));
				subscription.setFirst_name(result.getString("first_name"));
				subscription.setLast_name(result.getString("last_name"));
				subscription.setTitle(result.getString("title"));
				subscriptions.add(subscription);
			}

			result.close();
			statement.close();
			connection.close();

		} catch (SQLException|IOException|PropertyVetoException e) {
			e.printStackTrace();
		}

		return subscriptions;

	}

	public void addSubscription(int user_id, int periodical_id) {

		String template = "insert into subscriptions (date, user_id, periodical_id) values ('2015-12-11',"
				+ user_id + "," + periodical_id + ");";

		try {
			Connection connection = DataSource.getInstance().getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(template);

			statement.close();
			connection.close();

		} catch (SQLException|IOException|PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	public void removeSubscription(int id) {

		String template = "DELETE FROM subscriptions WHERE id=" + id + ";";

		try {
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
