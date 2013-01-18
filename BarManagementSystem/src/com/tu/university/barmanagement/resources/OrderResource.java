package com.tu.university.barmanagement.resources;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
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

import com.google.gson.JsonSyntaxException;
import com.tu.university.barmanagement.controler.UserControler;
import com.tu.university.barmanagement.exception.GetUserException;
import com.tu.university.barmanagement.managers.OrderManager;
import com.tu.university.barmanagement.model.Order;
import com.tu.university.barmanagement.model.User;
import com.tu.university.barmanagement.result.JsonObject;
import com.tu.university.barmanagement.result.Message;
import com.tu.university.barmanagement.result.Result;

@Stateless
@Path("order")
public class OrderResource {
	@Context
	private UriInfo context;

	@EJB
	OrderManager em;

	@EJB
	private UserControler userControl;
	/**
	 * Default constructor.
	 */
	public OrderResource() {
		// nothing to do
	}

	/**
	 * Retrieves representation of an Orders's instances
	 * 
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@RolesAllowed({"barman","waiter", "manager"})
	@Produces(MediaType.APPLICATION_JSON)
	public String geAlltOrders() {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<List<Order>> result = new Result<List<Order>>();
		try {
			result.setData(em.getAllOrders());
			message.setData("The Orders was retrieved successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the Orders.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		}
		return result.toJson();
	}
	/**
	 * Retrieves representation of an Order's instance by ID
	 * 
	 * @param id
	 *            The Order's id
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@RolesAllowed({"waiter", "manager"})
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOrder(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<Order> result = new Result<Order>();
		try {
			result.setData(em.getOrderById(id));
			if (result.getData() == null) {
				message.setData("The Order doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			} else {
				message.setData("The Order was retrieved successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			}
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the Order.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			Message messageException = new Message();
			messageException.setData(e.getMessage());
			messages.add(messageException);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		}
		return result.toJson();
	}
	/**
	 * PUT method for updating an instance of Order
	 * 
	 * @param content
	 *            representation for the resource
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@PUT
	@RolesAllowed({"waiter", "manager"})
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateOrder(@PathParam("id") Integer id, String order) {
		Order ordrOriginal = null;
		Order ordrNew = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<Order> result = new Result<Order>();

		try {
			ordrOriginal = em.getOrderById(id);
			if (ordrOriginal == null) {
				message.setData("The Order doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
				return result.toJson();
			} else {
				ordrNew = JsonObject.parseJson(order, Order.class);
				ordrOriginal.update(ordrNew);
			}
		} catch (JsonSyntaxException e) {
			message.setData("Erro occured while parsing the Json Order to object. Please check the Json syntax.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (NullPointerException e) {
			message.setData("The new Order can not be empty! Empty Json object!");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (Exception e) {
			message.setData("ERROR occured while retrieving the Order and reinitialize the new one.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			Message messageException = new Message();
			messageException.setData(e.getMessage());
			messages.add(messageException);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		}
		try {
			User usr = this.userControl.getCurrentUser();
			ordrOriginal.setUserUpdated(usr);
			em.updateOrder(ordrOriginal);
			message.setData("The Order was updated successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (GetUserException e) {
			message.setData(e.getMessage());
			message.setStatus(Message.ERROR);
			messages.add(message);
			Message messageException = new Message();
			messageException.setData(e.getMessage());
			messages.add(messageException);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (Exception e) {
			message.setData("The Order already exists OR the problem is with jpa");
			message.setStatus(Message.ERROR);
			messages.add(message);
			Message messageException = new Message();
			messageException.setData(e.getMessage());
			messages.add(messageException);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		}
		return result.toJson();
	}
	/**
	 * Adds new Order
	 * 
	 * @param value
	 *            the Order's data
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@POST
	@RolesAllowed({"waiter", "manager"})
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addOrder(String order) {
		Order ordr = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		final Result<Order> result = new Result<Order>();
		try {
			ordr = JsonObject.parseJson(order, Order.class);
			if (ordr == null) {
				message.setData("The Json parsing returned a null object");
				message.setStatus(Message.ERROR);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.FAIL);
			} else {
				User usr = this.userControl.getCurrentUser();
				ordr.setUserCreated(usr);
				em.addOrder(ordr);
				message.setData("The Order was added successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			}
		} catch (GetUserException e) {
			message.setData(e.getMessage());
			message.setStatus(Message.ERROR);
			messages.add(message);
			Message messageException = new Message();
			messageException.setData(e.getMessage());
			messages.add(messageException);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (JsonSyntaxException e) {
			message.setData("Erro occured while parsing the Json Order to object. Please check the Json syntax.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (EJBTransactionRolledbackException e) {
			message.setData("There is problem with JPA persist.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (Exception e) {
			message.setData("Unknown ERROR occured while adding the Order.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
		}
		return result.toJson();
	}

	/**
	 * Removes Order with specified id from the database
	 * 
	 * @param id
	 *            the id of the Order to be removed
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@DELETE
	@RolesAllowed({"waiter", "manager"})
	@Path("/{id}")
	public String removeOrder(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<Order> result = new Result<Order>();
		try {
			em.deleteOrderById(id);
			message.setData("The Order was deleted successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while deleting the Order.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			Message messageException = new Message();
			messageException.setData(e.getMessage());
			messages.add(messageException);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		}
		return result.toJson();
	}

	@PUT
	@RolesAllowed({"barman"})
	@Path("/{id}/addbarman")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateOrderBarman(@PathParam("id") Integer id, String order) {
		Order ordrOriginal = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<Order> result = new Result<Order>();

		try {
			ordrOriginal = em.getOrderById(id);
			if (ordrOriginal == null) {
				message.setData("The Order doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
				return result.toJson();
			} else {
				User usr = this.userControl.getCurrentUser();
				ordrOriginal.setOrderBarman(usr);
			}
		} catch (GetUserException e) {
			message.setData(e.getMessage());
			message.setStatus(Message.ERROR);
			messages.add(message);
			Message messageException = new Message();
			messageException.setData(e.getMessage());
			messages.add(messageException);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (Exception e) {
			message.setData("ERROR occured while adding a barman to the order.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			Message messageException = new Message();
			messageException.setData(e.getMessage());
			messages.add(messageException);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		}
		message.setData("The Barman was added successfully.");
		message.setStatus(Message.INFO);
		messages.add(message);
		result.setMessages(messages);
		result.setStatus(Result.SUCCESS);
		return result.toJson();
	}
}
