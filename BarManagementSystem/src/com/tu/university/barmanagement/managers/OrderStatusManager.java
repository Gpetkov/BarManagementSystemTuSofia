package com.tu.university.barmanagement.managers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tu.university.barmanagement.model.OrderStatus;

@Stateless
public class OrderStatusManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;
	
	public void saveOrderStatus(OrderStatus orderStatus)
	{
		em.persist(orderStatus);
	}
}
