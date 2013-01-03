package com.tu.sofia.bg.model.entitymanagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.tu.sofia.bg.model.OrderStatus;

public class OrderStatusManager {
	private EntityManager em;

	public OrderStatusManager(EntityManagerFactory emf) {
		em = emf.createEntityManager();
	}

	/**
	 * Method used to create table.
	 * 
	 * @param table
	 *            Current table which have to be store in a database
	 */
	public void createOrderStatus(OrderStatus orderStatus) {
		System.out.println("Creation");
		em.getTransaction().begin();
		// Persist entity in persistence context
		em.persist(orderStatus);
		// Commit Transaction
		em.getTransaction().commit();
		System.out.println("OrderStatus created successfully");
	}
}
