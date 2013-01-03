package com.tu.sofia.bg.dummy;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.spi.PersistenceUnitTransactionType;

import com.tu.sofia.bg.model.OrderStatus;
import com.tu.sofia.bg.model.Table;
import com.tu.sofia.bg.model.entitymanagers.OrderStatusManager;
import com.tu.sofia.bg.model.entitymanagers.TableManager;

public class TestHibernate {
	private static final String TRANSACTION_TYPE = null;
    private static final String JDBC_DRIVER = null;
    private static final String JDBC_PASSWORD = null;
    private static final String JDBC_USER = null;
    private static final String JDBC_URL = null;

    public static void main(String[] args) {
//	    Map<String, Object> properties = new HashMap<String, Object>();
//
//	    properties.put(TRANSACTION_TYPE, PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
//	    properties.put(JDBC_DRIVER, driver);
//	    properties.put(JDBC_URL, db_url);
//	    properties.put(JDBC_USER, "userName");
//	    properties.put(JDBC_PASSWORD, "password");
        EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("OrderStatus");
	  //  EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENT_UNIT_NAME", properties);
		OrderStatusManager orderStatusManager = new OrderStatusManager(emf);
		
		// Table table = new Table();
		// table.setNumber(1);
		OrderStatus o = new OrderStatus();
		o.setValue("sadsa");
		orderStatusManager.createOrderStatus(o);
		emf.close();
	}
}
