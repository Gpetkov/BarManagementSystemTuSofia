package com.tu.university.barmanagement.resources;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.tu.university.barmanagement.managers.UserManager;
import com.tu.university.barmanagement.model.User;
import com.tu.university.barmanagement.result.JsonObject;
import com.tu.university.barmanagement.result.Message;
import com.tu.university.barmanagement.result.Result;

@Stateless
@PermitAll
@Path("user")
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
	 * Retrieves representation of an Users's instances
	 * 
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@Produces("application/json")
	public String geAlltUsers() {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<List<User>> result = new Result<List<User>>();
		try {
			result.setData(em.getAllUsers());
			message.setData("The Users was retrieved successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while getting the Users.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
		}
		return result.toJson();
	}
	/**
	 * Retrieves representation of an User's instance by ID
	 * 
	 * @param id
	 *            The user's id
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<User> result = new Result<User>();
		// User user = em.getUserById(id);
		// result.setData(user);
		try {
			result.setData(em.getUserById(id));
			message.setData("The User was retrieved successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while getting the User.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			Message messageException = new Message();
			messageException.setData(e.getMessage());
			messages.add(messageException);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
		}
		return result.toJson();
	}
	/**
	 * PUT method for updating or creating an instance of User
	 * 
	 * @param content
	 *            representation for the resource
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void putJson(String user) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Adds new user
	 * 
	 * @param value
	 *            the user's data
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addUser(String user) {
		User usr = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		final Result<User> result = new Result<User>();
		// User resultUser = JsonObject.parseJson(user, User.class);

		try {
			usr = JsonObject.parseJson(user, User.class);
		} catch (Exception e) {
			message.setData("Erro occured while parsing the Json User to object");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
		}

		if (usr == null) {
			message.setData("The Json parsing returned a null object");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
		} else {

			try {
				em.addUser(usr);
				message.setData("The User was added successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			} catch (Exception e) {
				message.setData("ERROR occured while adding the User.");
				message.setStatus(Message.ERROR);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.FAIL);
			}
		}
		// User usr = JsonObject.parseJson(user, User.class);
		// em.addUser(usr);
		return result.toJson();
	}

	/**
	 * Removes user with specified id from the database
	 * 
	 * @param id
	 *            the id of the user to be removed
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@DELETE
	@Path("/{id}")
	public String removeUser(@PathParam("id") Integer id) {
		// em.deleteUserById(id);

		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<User> result = new Result<User>();
		// User user = em.getUserById(id);
		// result.setData(user);
		try {
			em.deleteUserById(id);
			message.setData("The User was deleted successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while deleting the User.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			Message messageException = new Message();
			messageException.setData(e.getMessage());
			messages.add(messageException);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
		}
		return result.toJson();
	}
}
