package com.tu.sofia.bg.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tu.sofia.bg.model.Order;
import com.tu.sofia.bg.resource.result.JsonObject;
import com.tu.sofia.bg.resource.result.Result;

@Path(Resource.ORDER_RESOURCE)
public class OrderResource extends Resource {

	/**
	 * This method returns all Orders in the database
	 * 
	 * @return the result of the operation
	 */
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String getAll() {
		final Result<List<Order>> result = new Result<List<Order>>();

		return result.toJson();
	}
	/**
	 * This method returns current order by ID
	 * 
	 * @return the result of the operation
	 */
	@GET
	@Path("/{id}")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String get(@PathParam(value = "id") final int id) {
		final Result<Order> result = new Result<Order>();

		return result.toJson();
	}

	/**
	 * This method adds a new order
	 * 
	 * @param value
	 *            The data represents an Order object
	 * @return the result of the operation
	 */
	@POST
	@Consumes(value = {MediaType.APPLICATION_JSON})
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String add(final String value) {
		final Order order = JsonObject.parseJson(value, Order.class);
		final Result<String> result = new Result<String>();

		return result.toJson();
	}
	/**
	 * This method updates an order by given id
	 * 
	 * @param id
	 *            the id of the order to be updated
	 * @param value
	 *            the order's data
	 * @return the result of the operation
	 */
	@PUT
	@Path("/{id}")
	@Consumes(value = {MediaType.APPLICATION_JSON})
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String update(@PathParam(value = "id") final int id,
			final String value) {
		final Order order = JsonObject.parseJson(value, Order.class);
		final Result<String> result = new Result<String>();

		return result.toJson();
	}

	/**
	 * This method removes an order from the database
	 * 
	 * @param id
	 *            the id of the order to be deleted
	 * @return the result of the operation
	 */
	@DELETE
	@Path("/{id}")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String delete(@PathParam(value = "id") final int id) {
		final Result<String> result = new Result<String>();

		return result.toJson();
	}
}
