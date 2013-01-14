package com.tu.university.barmanagement.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tu.university.barmanagement.model.Table;

@Stateless
public class TableManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Table> getAllTables() {
		return em.createNamedQuery("Table.getAll").getResultList();
	}
	public Table getTableById(Integer id) {
		Table Table = em.find(Table.class, id);
		em.flush();
		return Table;
	}
	public void addTable(Table table) {
		em.persist(table);
		em.flush();
	}

	public void updateTable(Table table) {
		em.merge(table);
		em.flush();
	}
	public void deleteTableById(Integer id) {
		Table table = this.getTableById(id);
		if (table != null)
			em.remove(table);
	}
}
