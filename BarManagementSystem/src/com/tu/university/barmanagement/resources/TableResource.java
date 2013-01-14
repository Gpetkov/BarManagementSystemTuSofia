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
import com.tu.university.barmanagement.managers.TableManager;
import com.tu.university.barmanagement.model.Table;
import com.tu.university.barmanagement.result.JsonObject;
import com.tu.university.barmanagement.result.Message;
import com.tu.university.barmanagement.result.Result;

@Stateless
@Path("table")
public class TableResource {
	@Context
	private UriInfo context;

	@EJB
	TableManager em;
	/**
	 * Default constructor.
	 */
	public TableResource() {
		// nothing to do
	}

	/**
	 * Retrieves representation of an Tables's instances
	 * 
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String geAlltTables() {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<List<Table>> result = new Result<List<Table>>();
		try {
			result.setData(em.getAllTables());
			message.setData("The Tables was retrieved successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the Tables.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		}
		return result.toJson();
	}
	/**
	 * Retrieves representation of an Table's instance by ID
	 * 
	 * @param id
	 *            The Table's id
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTable(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<Table> result = new Result<Table>();
		try {
			result.setData(em.getTableById(id));
			if (result.getData() == null) {
				message.setData("The Table doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			} else {
				message.setData("The Table was retrieved successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			}
		} catch (Exception e) {
			message.setData("Problem occured while retrieving the Table.");
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
	 * PUT method for updating an instance of Table
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
	public String updateTable(@PathParam("id") Integer id, String table) {
		Table tblOriginal = null;
		Table tblNew = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<Table> result = new Result<Table>();

		try {
			tblOriginal = em.getTableById(id);
			if (tblOriginal == null) {
				message.setData("The Table doesn't exists!");
				message.setStatus(Message.WARNING);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
				return result.toJson();
			} else {
				tblNew = JsonObject.parseJson(table, Table.class);
				tblOriginal.update(tblNew);
			}
		} catch (Exception e) {
			message.setData("ERROR occured while retrieving the Table and reinitialize the new one.");
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
			em.updateTable(tblOriginal);
			message.setData("The Table was updated successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while updating the Table.");
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
	 * Adds new Table
	 * 
	 * @param value
	 *            the Table's data
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addTable(String table) {
		Table tbl = null;
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		final Result<Table> result = new Result<Table>();
		try {
			tbl = JsonObject.parseJson(table, Table.class);
			if (tbl == null) {
				message.setData("The Json parsing returned a null object");
				message.setStatus(Message.ERROR);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.FAIL);
			} else {
				em.addTable(tbl);
				System.out.println("After add");
				message.setData("The Table was added successfully.");
				message.setStatus(Message.INFO);
				messages.add(message);
				result.setMessages(messages);
				result.setStatus(Result.SUCCESS);
			}
		} catch (JsonSyntaxException e) {
			message.setData("Erro occured while parsing the Json Table to object. Please check the Json syntax.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (EJBTransactionRolledbackException e) {
			message.setData("The Table with Tablename: \"" + tbl.getTblNumber()
					+ "\" already exists OR there is problem with JPA persist.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
			return result.toJson();
		} catch (Exception e) {

			message.setData("Unknown ERROR occured while adding the Table.");
			message.setStatus(Message.ERROR);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.FAIL);
		}
		return result.toJson();
	}

	/**
	 * Removes Table with specified id from the database
	 * 
	 * @param id
	 *            the id of the Table to be removed
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@DELETE
	@Path("/{id}")
	public String removeTable(@PathParam("id") Integer id) {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<Table> result = new Result<Table>();
		try {
			em.deleteTableById(id);
			message.setData("The Table was deleted successfully.");
			message.setStatus(Message.INFO);
			messages.add(message);
			result.setMessages(messages);
			result.setStatus(Result.SUCCESS);
		} catch (Exception e) {
			message.setData("Problem occured while deleting the Table.");
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
