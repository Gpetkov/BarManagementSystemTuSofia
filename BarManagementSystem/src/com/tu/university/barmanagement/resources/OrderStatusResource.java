package com.tu.university.barmanagement.resources;

import java.util.ArrayList;
import java.util.List;

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
import com.tu.university.barmanagement.managers.OrderStatusManager;
import com.tu.university.barmanagement.model.OrderStatus;
import com.tu.university.barmanagement.model.User;
import com.tu.university.barmanagement.result.JsonObject;
import com.tu.university.barmanagement.result.Message;
import com.tu.university.barmanagement.result.Result;

@Stateless
@Path("orderstatus")
public class OrderStatusResource {
	@Context
	private UriInfo context;

	@EJB
	OrderStatusManager em;

	@EJB
	private UserControler userControl;

	/**
	 * Default constructor.
	 */
	public OrderStatusResource() {
		// nothing to do
	}

	/**
	 * Retrieves representation of an OrderStatuss's instances
	 * 
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String geAlltOrderStatus() {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<List<OrderStatus>> result = new Result<List<OrderStatus>>();
		try {
			result.setData(em.getAllOrderStatuss());
			message.setData("The OrderStatuss was retrieved successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the OrderStatuss.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		}
		return result.toJson();
	}
	/**
	 * Retrieves representation of an OrderStatus's instance by ID
	 * 
	 * @param id
	 *            The OrderStatus's id
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOrderStatus(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<OrderStatus> result = new Result<OrderStatus>();
		try {
			result.setData(em.getOrderStatusById(id));
			if (result.getData() == null) {
				message.setData("The OrderStatus doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			} else {
				message.setData("The OrderStatus was retrieved successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			}
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the OrderStatus.");
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
	 * PUT method for updating an instance of OrderStatus
	 * 
	 * @param content
	 *            representation for the resource
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateOrderStatus(@PathParam("id") Integer id,
			String orderStatus) {
		OrderStatus orderStatusOriginal = null;
		OrderStatus orderStatusNew = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<OrderStatus> result = new Result<OrderStatus>();

		try {
			orderStatusOriginal = em.getOrderStatusById(id);
			if (orderStatusOriginal == null) {
				message.setData("The OrderStatus doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
				return result.toJson();
			} else {
				orderStatusNew = JsonObject.parseJson(orderStatus,
						OrderStatus.class);
				orderStatusOriginal.update(orderStatusNew);
			}
		} catch (JsonSyntaxException e) {
			message.setData("Erro occured while parsing the Json Order Status to object. Please check the Json syntax.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (NullPointerException e) {
			message.setData("The new Order Status can not be empty! Empty Json object!");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (Exception e) {
			message.setData("ERROR occured while retrieving the OrderStatus and reinitialize the new one.");
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
			orderStatusOriginal.setUserUpdated(usr);
			em.updateOrderStatus(orderStatusOriginal);
			message.setData("The OrderStatus was updated successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while updating the OrderStatus.");
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
	 * Adds new OrderStatus
	 * 
	 * @param value
	 *            the OrderStatus's data
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addOrderStatus(String orderStatus) {
		OrderStatus ordStatus = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		final Result<OrderStatus> result = new Result<OrderStatus>();
		try {
			ordStatus = JsonObject.parseJson(orderStatus, OrderStatus.class);
			if (ordStatus == null) {
				message.setData("The Json parsing returned a null object");
				message.setStatus(Message.ERROR);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.FAIL);
			} else {
				User usr = this.userControl.getCurrentUser();
				ordStatus.setUserCreated(usr);
				em.addOrderStatus(ordStatus);
				System.out.println("After add");
				message.setData("The OrderStatus was added successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			}
		} catch (JsonSyntaxException e) {
			message.setData("Erro occured while parsing the Json OrderStatus to object. Please check the Json syntax.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (EJBTransactionRolledbackException e) {
			message.setData("The OrderStatus with OrderStatusname: \""
					+ ordStatus.getOrdrstValue()
					+ "\" already exists OR there is problem with JPA persist.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (Exception e) {

			message.setData("Unknown ERROR occured while adding the OrderStatus.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
		}
		return result.toJson();
	}

	/**
	 * Removes OrderStatus with specified id from the database
	 * 
	 * @param id
	 *            the id of the OrderStatus to be removed
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@DELETE
	@Path("/{id}")
	public String removeOrderStatus(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<OrderStatus> result = new Result<OrderStatus>();
		try {
			em.deleteOrderStatusById(id);
			message.setData("The OrderStatus was deleted successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while deleting the OrderStatus.");
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