package org.numisoft.webproject.dao;

import org.numisoft.webproject.dto.Collectible;
import org.numisoft.webproject.dto.User;

import java.util.List;

public interface CollectibleDao {

	List<Collectible> getCollectiblesByUser(User user);

	void addCollectible(int user_id, int periodical_id);

	void removeCollectible(int id);

}
