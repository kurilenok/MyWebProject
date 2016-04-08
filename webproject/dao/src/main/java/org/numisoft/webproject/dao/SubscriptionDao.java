package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Subscription;
import org.numisoft.webproject.dto.User;

import java.util.List;

public interface SubscriptionDao {

	List<Subscription> getSubscriptionByUser(User user);

	void addSubscription(int user_id, int periodical_id);

	void removeSubscription(int id);

}
