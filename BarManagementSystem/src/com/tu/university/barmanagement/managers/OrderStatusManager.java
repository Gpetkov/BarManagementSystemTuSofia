package com.tu.university.barmanagement.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tu.university.barmanagement.model.OrderStatus;

@Stateless
public class OrderStatusManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<OrderStatus> getAllOrderStatuss() {
		return em.createNamedQuery("OrderStatus.getAll").getResultList();
	}
	public OrderStatus getOrderStatusById(Integer id) {
		OrderStatus OrderStatus = em.find(OrderStatus.class, id);
		em.flush();
		return OrderStatus;
	}
	public Integer addOrderStatus(OrderStatus orderStatus) {
		em.persist(orderStatus);
		em.flush();
		return orderStatus.getOrdstId();
	}

	public void updateOrderStatus(OrderStatus orderStatus) {
		em.merge(orderStatus);
		em.flush();
	}
	public void deleteOrderStatusById(Integer id) {
		OrderStatus OrderStatus = this.getOrderStatusById(id);
		if (OrderStatus != null)
			em.remove(OrderStatus);
	}
}
