package com.tu.sofia.bg.dummy;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tu.sofia.bg.model.OrderStatus;
import com.tu.sofia.bg.model.Table;
import com.tu.sofia.bg.model.entitymanagers.OrderStatusManager;
import com.tu.sofia.bg.model.entitymanagers.TableManager;

public class TestHibernate {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("OrderStatus");
		OrderStatusManager orderStatusManager = new OrderStatusManager(emf);

		// Table table = new Table();
		// table.setNumber(1);
		OrderStatus o = new OrderStatus();
		o.setValue("sadsa");
		orderStatusManager.createOrderStatus(o);
		emf.close();
	}
}
