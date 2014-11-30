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
import com.tu.university.barmanagement.controller.UserController;
import com.tu.university.barmanagement.managers.OrderToItemManager;
import com.tu.university.barmanagement.model.OrderToItem;
import com.tu.university.barmanagement.result.JsonObject;
import com.tu.university.barmanagement.result.Message;
import com.tu.university.barmanagement.result.Result;

@Stateless
@Path("ordertoitem")
public class OrderToItemResource {
	@Context
	private UriInfo context;

	@EJB
	OrderToItemManager em;

	@EJB
	private UserController userControl;

	/**
	 * Default constructor.
	 */
	public OrderToItemResource() {
		// nothing to do
	}

	/**
	 * Retrieves representation of an OrderItem instances
	 * 
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@RolesAllowed({"waiter", "manager"})
	@Produces(MediaType.APPLICATION_JSON)
	public String geAlltOrderToItems() {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<List<OrderToItem>> result = new Result<List<OrderToItem>>();
		try {
			result.setData(em.getAllOrderToItems());
			message.setData("The Order Items was retrieved successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the order to item.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		}
		return result.toJson();
	}
	/**
	 * Retrieves representation of an OrderToItem's instance by ID
	 * 
	 * @param id
	 *            The item's id
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@RolesAllowed({"waiter", "manager"})
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOrderToItemById(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<OrderToItem> result = new Result<OrderToItem>();
		try {
			result.setData(em.getOrderToItemById(id));
			if (result.getData() == null) {
				message.setData("The OrderToItem doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			} else {
				message.setData("The OrderToItem was retrieved successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			}
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the OrderToItem.");
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
	 * PUT method for updating an instance of OrderToItem
	 * 
	 * @param content
	 *            representation for the resource
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@PUT
	@RolesAllowed({"waiter"})
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateOrderToItem(@PathParam("id") Integer id, String item) {
		OrderToItem ordrToItemOriginal = null;
		OrderToItem ordrToItemNew = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<OrderToItem> result = new Result<OrderToItem>();

		try {
			ordrToItemOriginal = em.getOrderToItemById(id);
			if (ordrToItemOriginal == null) {
				message.setData("The Item doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
				return result.toJson();
			} else {
				ordrToItemNew = JsonObject.parseJson(item, OrderToItem.class);
				ordrToItemOriginal.update(ordrToItemNew);
			}
		} catch (JsonSyntaxException e) {
			message.setData("Erro occured while parsing the Json OrderToItem to object. Please check the Json syntax.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (NullPointerException e) {
			message.setData("The new OrderToItem can not be empty! Empty Json object!");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (Exception e) {
			message.setData("ERROR occured while retrieving the Item and reinitialize the new one.");
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

			em.updateOrderToItem(ordrToItemOriginal);
			message.setData("The OrderToItem was updated successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("The OrderToItem already exists OR the problem is with jpa");
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
	 * Adds new OrderToItem
	 * 
	 * @param value
	 *            the OrderToItem's data
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@POST
	@RolesAllowed({"waiter"})
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addOrderToItem(String item) {
		OrderToItem ordToItem = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		final Result<OrderToItem> result = new Result<OrderToItem>();
		try {
			ordToItem = JsonObject.parseJson(item, OrderToItem.class);
			if (ordToItem == null) {
				message.setData("The Json parsing returned a null object");
				message.setStatus(Message.ERROR);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.FAIL);
			} else {
				Integer id = em.addOrderToItem(ordToItem);
				ordToItem.setItmId(id);
				message.setData("The OrderToItem was added successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
				result.setData(ordToItem);
			}
		} catch (JsonSyntaxException e) {
			message.setData("Erro occured while parsing the Json OrderToItem to object. Please check the Json syntax.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (EJBTransactionRolledbackException e) {
			message.setData("The OrderToItem with OrderToItem id: \""
					+ ordToItem.getOrderToItemId()
					+ "\" already exists OR there is problem with JPA persist.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (Exception e) {
			message.setData("Unknown ERROR occured while adding the Item.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
		}
		return result.toJson();
	}

	/**
	 * Removes OrderToItem with specified id from the database
	 * 
	 * @param id
	 *            the id of the OrderToItem to be removed
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@DELETE
	@RolesAllowed({"waiter"})
	@Path("/{id}")
	public String removeItem(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<OrderToItem> result = new Result<OrderToItem>();
		try {
			em.deleteOrderToItemById(id);
			message.setData("The OrderToItem was deleted successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while deleting the OrderToItem.");
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
}
