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
		return (List<User>) em.createNamedQuery("User.getAll").getResultList();
	}

	public User getUserById(Integer id) {
		User user = (User)em.find(User.class, id);
		em.flush();
		return user;
	}
	public void addUser(User user) {
		em.persist(user);
		em.flush();
	}

	public void updateUser(User user) {
		em.merge(user);
		em.flush();
	}
	public void deleteUserById(Integer id) {
		User user = (User)this.getUserById(id);
		if (user != null)
			em.remove(user);
	}
}
