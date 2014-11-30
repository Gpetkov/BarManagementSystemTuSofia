package com.tu.university.barmanagement.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.tu.university.barmanagement.exception.GetUserException;
import com.tu.university.barmanagement.model.User;

@Stateless
public class UserController {

	@Context
	HttpServletRequest request;
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

	public boolean isInjected() {
		return request == null ? false : true;
	}

	public User getCurrentUser() throws GetUserException {
		User user = null;
		try {
			String userName = request.getRemoteUser();
			user = (User) em.createNamedQuery("User.getByUserName")
					.setParameter("usrUsername", userName).getSingleResult();
		} catch (Exception e) {
			throw new GetUserException(
					"ERROR occured while retrieving the request's user");
		}
		return user;
	}
}
