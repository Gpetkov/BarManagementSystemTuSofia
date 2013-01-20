package com.tu.university.barmanagement.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tu.university.barmanagement.model.Order;
import com.tu.university.barmanagement.model.User;

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
	
	@SuppressWarnings("unchecked")
	public List<Order> getAllOrdersWithoutBarman() {
		javax.persistence.Query q = em.createNamedQuery("findOrdersWithoutBarman");
		//q = q.setParameter(1, null);
		List<Order> result = q.getResultList();
		return result;
	}
	@SuppressWarnings("unchecked")
	public List<Order> getAllBarmanOrders(User usr) {
		javax.persistence.Query q = em.createNamedQuery("findBarmanOrders");
		//q = q.setParameter(1, null);
		q.setParameter("ordbarman", usr);
		List<Order> result = q.getResultList();
		return result;
	}
}
