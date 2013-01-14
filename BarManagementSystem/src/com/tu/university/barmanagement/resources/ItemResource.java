package com.tu.university.barmanagement.resources;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
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
import com.tu.university.barmanagement.managers.ItemManager;
import com.tu.university.barmanagement.model.Item;
import com.tu.university.barmanagement.model.User;
import com.tu.university.barmanagement.result.JsonObject;
import com.tu.university.barmanagement.result.Message;
import com.tu.university.barmanagement.result.Result;

@Stateless
@Path("item")
@PermitAll
public class ItemResource {
	@Context
	private UriInfo context;

	@EJB
	ItemManager em;
	
	@EJB
	private  UserControler userControl;
	/**
	 * Default constructor.
	 */
	public ItemResource() {
		//nothing to do
	}

	/**
	 * Retrieves representation of an Items's instances
	 * 
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String geAlltItems() {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<List<Item>> result = new Result<List<Item>>();
		try {
			result.setData(em.getAllItems());
			message.setData("The Items was retrieved successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the items.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		}
		return result.toJson();
	}
	/**
	 * Retrieves representation of an Item's instance by ID
	 * 
	 * @param id
	 *            The item's id
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@RolesAllowed(value="manager")
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getItem(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<Item> result = new Result<Item>();
		try {
			//System.out.println(em.getItemById(id).getBmUser2().getUsrUsername());
			result.setData(em.getItemById(id));
			if (result.getData() == null) {
				message.setData("The Item doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			} else {
				message.setData("The Item was retrieved successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			}
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the Item.");
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
	 * PUT method for updating an instance of Item
	 * 
	 * @param content
	 *            representation for the resource
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@PUT
	@RolesAllowed(value="manager")
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateItem(@PathParam("id") Integer id, String item) {
		Item itmOriginal = null;
		Item itmNew = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<Item> result = new Result<Item>();

		try {
			itmOriginal = em.getItemById(id);
			if (itmOriginal == null) {
				message.setData("The Item doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
				return result.toJson();
			} else {
				itmNew = JsonObject.parseJson(item, Item.class);
				itmOriginal.update(itmNew);
			}
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
			System.out.println(userControl.isInjected());
			User usr = this.userControl.getCurrentUser();
			System.out.println(usr.getUsrUsername());
			itmOriginal.setBmUser2(this.userControl.getCurrentUser());
			System.out.println("");
			em.updateItem(itmOriginal);
			message.setData("The Item was updated successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("The item already exists OR the problem is with jpa");
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
	 * Adds new Item
	 * 
	 * @param value
	 *            the Item's data
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addItem(String item) {
		Item itm = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		final Result<Item> result = new Result<Item>();
		try {
			itm = JsonObject.parseJson(item, Item.class);
			if (itm == null) {
				message.setData("The Json parsing returned a null object");
				message.setStatus(Message.ERROR);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.FAIL);
			} else {
				// itm.setBmUser1(context.)
				em.addItem(itm);
				System.out.println("After add");
				message.setData("The Item was added successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			}
		} catch (JsonSyntaxException e) {
			message.setData("Erro occured while parsing the Json Item to object. Please check the Json syntax.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (EJBTransactionRolledbackException e) {
			message.setData("The Item with Itemname: \"" + itm.getItmName()
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
	 * Removes Item with specified id from the database
	 * 
	 * @param id
	 *            the id of the Item to be removed
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@DELETE
	@Path("/{id}")
	public String removeItem(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<Item> result = new Result<Item>();
		try {
			em.deleteItemById(id);
			message.setData("The Item was deleted successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while deleting the Item.");
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
