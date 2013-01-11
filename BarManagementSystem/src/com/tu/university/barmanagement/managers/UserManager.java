package com.tu.university.barmanagement.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tu.university.barmanagement.model.User;

@Stateless
public class UserManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return em.createNamedQuery("User.getAll").getResultList();
	}

	public User getUserById(Integer id) {
		User user = em.find(User.class, id);
		return user;
	}
	public void addUser(User user) {
		em.persist(user);
	}

	public void deleteUserById(Integer id) {
		User user = this.getUserById(id);
		if (user != null)
			em.remove(user);
	}

}
