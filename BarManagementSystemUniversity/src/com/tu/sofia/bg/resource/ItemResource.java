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

import com.tu.sofia.bg.model.Item;
import com.tu.sofia.bg.resource.result.JsonObject;
import com.tu.sofia.bg.resource.result.Result;

@Path(Resource.ITEM_RESOURCE)
public class ItemResource extends Resource {

	/**
	 * This method returns all items in the database
	 * 
	 * @return the result of the operation
	 */
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String getAll() {
		final Result<List<Item>> result = new Result<List<Item>>();

		return result.toJson();
	}
	/**
	 * This method returns current item by ID
	 * 
	 * @return the result of the operation
	 */
	@GET
	@Path("/{id}")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String get(@PathParam(value = "id") final int id) {
		final Result<Item> result = new Result<Item>();

		return result.toJson();
	}

	/**
	 * This method adds a new item
	 * 
	 * @param value
	 *            The data represents an Item object
	 * @return the result of the operation
	 */
	@POST
	@Consumes(value = {MediaType.APPLICATION_JSON})
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String add(final String value) {
		final Item item = JsonObject.parseJson(value, Item.class);
		final Result<String> result = new Result<String>();

		return result.toJson();
	}
	/**
	 * This method updates an item by given id
	 * 
	 * @param id
	 *            the id of the item to be updated
	 * @param value
	 *            the item's data
	 * @return the result of the operation
	 */
	@PUT
	@Path("/{id}")
	@Consumes(value = {MediaType.APPLICATION_JSON})
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String update(@PathParam(value = "id") final int id,
			final String value) {
		final Item item = JsonObject.parseJson(value, Item.class);
		final Result<String> result = new Result<String>();

		return result.toJson();
	}

	/**
	 * This method removes an item from the database
	 * 
	 * @param id
	 *            the id of the item to be deleted
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
