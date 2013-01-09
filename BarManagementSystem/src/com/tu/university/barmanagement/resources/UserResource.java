package com.tu.university.barmanagement.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.tu.university.barmanagement.managers.UserManager;
import com.tu.university.barmanagement.model.User;
import com.tu.university.barmanagement.result.JsonObject;
import com.tu.university.barmanagement.result.Result;

@Stateless
@Path("userpath")
public class UserResource {
	@Context
	private UriInfo context;

	@EJB
	UserManager em;
	/**
	 * Default constructor.
	 */
	public UserResource() {
		// nothing to do
	}

	/**
	 * Retrieves representation of an instance of OrderStatusResource
	 * 
	 * @return an instance of String
	 */
	@GET
	@Produces("application/json")
	public String geAlltUsers() {
		Result<List<User>> result = new Result<List<User>>();
		result.setData(em.getAllUsers());
		return result.toJson();
	}

	/**
	 * PUT method for updating or creating an instance of OrderStatusResource
	 * 
	 * @param content
	 *            representation for the resource
	 * @return an HTTP response with content of the updated or created resource.
	 */
	@PUT
	@Consumes("application/json")
	public void putJson(String content) {
		throw new UnsupportedOperationException();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addOrderStatus(String user) {
		//final Result<User> result1 = new Result<User>();
	//	User resultUser = JsonObject.parseJson(user, User.class);
		
		User usr = JsonObject.parseJson(user, User.class);
		em.addUser(usr);
		return "Hi there " + user;
	}
}
