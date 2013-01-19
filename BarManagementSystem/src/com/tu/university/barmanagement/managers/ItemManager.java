package com.tu.university.barmanagement.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tu.university.barmanagement.model.Item;

@Stateless
public class ItemManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Item> getAllItems() {
		return em.createNamedQuery("Item.getAll").getResultList();
	}
	public Item getItemById(Integer id) {
		Item item = em.find(Item.class, id);
		em.flush();
		return item;
	}
	public Integer addItem(Item item) {
		em.persist(item);
		em.flush();
		return item.getItmId();
	}

	public void updateItem(Item item) {
		em.merge(item);
		em.flush();
	}
	public void deleteItemById(Integer id) {
		Item item = this.getItemById(id);
		if (item != null)
			em.remove(item);
	}
}
