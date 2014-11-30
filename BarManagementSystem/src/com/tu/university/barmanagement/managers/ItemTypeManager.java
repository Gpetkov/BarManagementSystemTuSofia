package com.tu.university.barmanagement.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tu.university.barmanagement.model.ItemType;

@Stateless
public class ItemTypeManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ItemType> getAllItemTypes() {
		return (List<ItemType>) em.createNamedQuery("ItemType.getAll")
				.getResultList();
	}

	public ItemType getItemTypeByName(String name) {
		return (ItemType) em.createNamedQuery("ItemType.getByName").setParameter("itmName", name)
				.getSingleResult();
	}
	
	public ItemType getItemTypeById(Integer id) {
		ItemType user = (ItemType) em.find(ItemType.class, id);
		em.flush();
		return user;
	}

	public Integer addItemType(ItemType itemType) {
		em.persist(itemType);
		em.flush();
		return itemType.getId();
	}

	public void updateItemType(ItemType itemType) {
		em.merge(itemType);
		em.flush();
	}

	public void deleteItemTypeById(Integer id) {
		ItemType itemType = (ItemType) this.getItemTypeById(id);
		if (itemType != null)
			em.remove(itemType);
	}
}