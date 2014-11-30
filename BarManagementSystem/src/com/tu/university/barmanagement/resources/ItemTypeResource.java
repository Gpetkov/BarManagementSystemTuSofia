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
import com.tu.university.barmanagement.controller.UserController;
import com.tu.university.barmanagement.exception.GetUserException;
import com.tu.university.barmanagement.managers.ItemTypeManager;
import com.tu.university.barmanagement.model.ItemType;
import com.tu.university.barmanagement.model.User;
import com.tu.university.barmanagement.result.JsonObject;
import com.tu.university.barmanagement.result.Message;
import com.tu.university.barmanagement.result.Result;

@Stateless
@Path("itemtype")
//@RolesAllowed({ "manager" })
public class ItemTypeResource {
	@Context
	private UriInfo context;

	@EJB
	ItemTypeManager em;

	@EJB
	private UserController userControl;

	/**
	 * Default constructor.
	 */
	public ItemTypeResource() {
		// nothing to do
	}

	/**
	 * Retrieves representation of an ItemType's instances
	 * 
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String geAlltItemTypes() {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<List<ItemType>> result = new Result<List<ItemType>>();
		try {
			result.setData(em.getAllItemTypes());
			message.setData("The ItemTypes was retrieved successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the ItemTypes.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		}
		return result.toJson();
	}

	/**
	 * Retrieves representation of an ItemType's instance by ID
	 * 
	 * @param id
	 *            The ItemType's id
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getItemType(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<ItemType> result = new Result<ItemType>();
		try {
			result.setData(em.getItemTypeById(id));
			if (result.getData() == null) {
				message.setData("The ItemType doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			} else {
				message.setData("The ItemType was retrieved successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			}
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the ItemType.");
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
	 * PUT method for updating an instance of ItemType
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
	public String updateItemType(@PathParam("id") Integer id, String itemType) {
		ItemType itmtypeOriginal = null;
		ItemType itmtypeNew = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<ItemType> result = new Result<ItemType>();

		try {
			itmtypeOriginal = em.getItemTypeById(id);
			if (itmtypeOriginal == null) {
				message.setData("The ItemType doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
				return result.toJson();
			} else {
				itmtypeNew = JsonObject.parseJson(itemType, ItemType.class);
				itmtypeOriginal.setItmType(itmtypeNew.getItmType());
			}
		} catch (JsonSyntaxException e) {
			message.setData("Erro occured while parsing the Json ItemType to object. Please check the Json syntax.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (NullPointerException e) {
			message.setData("The new ItemType can not be empty! Empty Json object!");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (Exception e) {
			message.setData("ERROR occured while retrieving the ItemType and reinitialize the new one.");
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
			itmtypeOriginal.setUserUpdated(usr);
			em.updateItemType(itmtypeOriginal);
			message.setData("The ItemType was updated successfully.");
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
			message.setData("Problem occured while updating the ItemType.");
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
	 * Adds new ItemType
	 * 
	 * @param value
	 *            the ItemType's data
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addItemType(String itemType) {
		ItemType itmType = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		final Result<ItemType> result = new Result<ItemType>();
		try {
			itmType = JsonObject.parseJson(itemType, ItemType.class);
			if (itmType == null) {
				message.setData("The Json parsing returned a null object");
				message.setStatus(Message.ERROR);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.FAIL);
			} else {
				User userCreate = this.userControl.getCurrentUser();
				itmType.setUserCreated(userCreate);
				Integer id = em.addItemType(itmType);
				itmType.setId(id);
				System.out.println("After add");
				message.setData("The ItemType was added successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
				result.setData(itmType);
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
			message.setData("Erro occured while parsing the Json ItemType to object. Please check the Json syntax.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (EJBTransactionRolledbackException e) {
			message.setData("The ItemType with name: \"" + itmType.getItmType()
					+ "\" already exists OR there is problem with JPA persist.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (Exception e) {

			message.setData("Unknown ERROR occured while adding the ItemType.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
		}
		return result.toJson();
	}

	/**
	 * Removes ItemType with specified id from the database
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
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<ItemType> result = new Result<ItemType>();
		try {
			em.deleteItemTypeById(id);
			message.setData("The ItemType was deleted successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while deleting the ItemType.");
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