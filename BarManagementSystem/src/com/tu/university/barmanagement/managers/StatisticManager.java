package com.tu.university.barmanagement.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tu.university.barmanagement.model.UserStatistic;

@Stateless
public class StatisticManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<UserStatistic> getWaitersStatistic() {
		List<UserStatistic> result = new ArrayList<UserStatistic>();
		javax.persistence.Query q = em
				.createNativeQuery("SELECT u.usr_username as username, count(*) AS total FROM bm_order o left join bm_user u on o.ordr_created_by_bm_user_id = u.usr_id GROUP BY username ORDER BY total");

		List<Object[]> resultList = q.getResultList();
		for (Object[] current : resultList) {
			UserStatistic userStatistic = new UserStatistic();
			userStatistic.setName(current[0].toString());
			userStatistic.setData(Integer.valueOf(current[1].toString()));
			result.add(userStatistic);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<UserStatistic> getBarmansStatistic() {
		List<UserStatistic> result = new ArrayList<UserStatistic>();
		javax.persistence.Query q = em
				.createNativeQuery("SELECT u.usr_username as username, count(*) AS total "
						+ "FROM bm_order o left join bm_user u on o.ORDR_BARMAN_ID = u.usr_id "
						+ "where u.usr_username != 'null' GROUP BY username ORDER BY total");

		List<Object[]> resultList = q.getResultList();
		for (Object[] current : resultList) {
			UserStatistic userStatistic = new UserStatistic();
			userStatistic.setName(current[0].toString());
			userStatistic.setData(Integer.valueOf(current[1].toString()));
			result.add(userStatistic);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<UserStatistic> getItemsStatistic() {
		List<UserStatistic> result = new ArrayList<UserStatistic>();
		javax.persistence.Query q = em
				.createNativeQuery("SELECT i.itm_name as itemName, count(*) AS total "
						+ "FROM bm_order_to_bm_item oti left join bm_item i on oti.itm_id = i.itm_id "
						+ "GROUP BY itemName ORDER BY total");

		List<Object[]> resultList = q.getResultList();
		for (Object[] current : resultList) {
			UserStatistic userStatistic = new UserStatistic();
			userStatistic.setName(current[0].toString());
			userStatistic.setData(Integer.valueOf(current[1].toString()));
			result.add(userStatistic);
		}
		return result;
	}
}
