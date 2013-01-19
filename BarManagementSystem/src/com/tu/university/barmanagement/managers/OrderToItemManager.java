package com.tu.university.barmanagement.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tu.university.barmanagement.model.OrderToItem;

@Stateless
public class OrderToItemManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<OrderToItem> getAllOrderToItems() {
		return em.createNamedQuery("OrderToItem.getAll").getResultList();
	}
	public OrderToItem getOrderToItemById(Integer id) {
		OrderToItem orderToItem = em.find(OrderToItem.class, id);
		em.flush();
		return orderToItem;
	}
	public Integer addOrderToItem(OrderToItem orderToItem) {
		em.persist(orderToItem);
		em.flush();
		return orderToItem.getItmId();
	}

	public void updateOrderToItem(OrderToItem OrderToItem) {
		em.merge(OrderToItem);
		em.flush();
	}
	public void deleteOrderToItemById(Integer id) {
		OrderToItem OrderToItem = this.getOrderToItemById(id);
		if (OrderToItem != null)
			em.remove(OrderToItem);
	}
}
