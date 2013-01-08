package com.tu.university.barmanagement.managers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

}
