package com.tu.university.barmanagement.resources;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.tu.university.barmanagement.managers.StatisticManager;
import com.tu.university.barmanagement.model.UserStatistic;
import com.tu.university.barmanagement.result.Message;
import com.tu.university.barmanagement.result.Result;

@Stateless
@Path("statistic")
@RolesAllowed({ "manager" })
public class StatisticResource {
	@Context
	private UriInfo context;

	@EJB
	private StatisticManager em;

	/**
	 * Default constructor.
	 */
	public StatisticResource() {
		// nothing to do
	}

	/**
	 * Retrieves count of the waiters orders
	 * 
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@RolesAllowed({ "manager" })
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/waiter")
	public String geStatisticWaiters() {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<List<UserStatistic>> result = new Result<List<UserStatistic>>();
		try {
			result.setData(em.getWaitersStatistic());
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
	 * Retrieves count of the barmans orders
	 * 
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@RolesAllowed({ "manager" })
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/barman")
	public String geStatisticBarmans() {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<List<UserStatistic>> result = new Result<List<UserStatistic>>();
		try {
			result.setData(em.getBarmansStatistic());
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
	 * Retrieves count of the barmans orders
	 * 
	 * @return the result of the operation with message and status
	 * @see Message
	 * @see Result
	 */
	@GET
	@RolesAllowed({ "manager" })
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/item")
	public String geStatisticItems() {
		Message message = new Message();
		List<Message> messages = new ArrayList<Message>();
		Result<List<UserStatistic>> result = new Result<List<UserStatistic>>();
		try {
			result.setData(em.getItemsStatistic());
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
}
