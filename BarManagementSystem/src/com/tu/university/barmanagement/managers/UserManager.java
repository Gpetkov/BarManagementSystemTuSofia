package com.tu.university.barmanagement.managers;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;

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
		em.flush();
		return user;
	}
	public void addUser(User user) throws ConstraintViolationException,
			PSQLException {
		em.persist(user);
		em.flush();
	}

	public void updateUser(User user) {
		em.merge(user);
		em.flush();
	}
	public void deleteUserById(Integer id) {
		User user = this.getUserById(id);
		if (user != null)
			em.remove(user);
	}
}
