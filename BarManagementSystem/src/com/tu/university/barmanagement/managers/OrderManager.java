package com.tu.university.barmanagement.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tu.university.barmanagement.model.Order;

@Stateless
public class OrderManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Order> getAllOrders() {
		return em.createNamedQuery("Order.getAll").getResultList();
	}
	public Order getOrderById(Integer id) {
		Order order = em.find(Order.class, id);
		em.flush();
		return order;
	}
	public Integer addOrder(Order order) {
		em.persist(order);
		em.flush();
		return order.getOrdId();
	}

	public void updateOrder(Order order) {
		
		em.merge(order);
		em.flush();
	}
	public void deleteOrderById(Integer id) {
		Order order = this.getOrderById(id);
		if (order != null)
			em.remove(order);
	}
	
	
	
}
