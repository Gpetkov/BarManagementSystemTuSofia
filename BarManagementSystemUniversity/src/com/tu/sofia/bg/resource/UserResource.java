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
import com.tu.sofia.bg.model.User;
import com.tu.sofia.bg.resource.result.JsonObject;
import com.tu.sofia.bg.resource.result.Result;

@Path(Resource.USER_RESOURCE)
public class UserResource extends Resource {
	/**
	 * This method returns all users in the database
	 * 
	 * @return the result of the operation
	 */
	@GET
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String getAll() {
		final Result<List<User>> result = new Result<List<User>>();

		return result.toJson();
	}
	/**
	 * This method returns current user by ID
	 * 
	 * @return the result of the operation
	 */
	@GET
	@Path("/{id}")
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String get(@PathParam(value = "id") final int id) {
		final Result<User> result = new Result<User>();

		return result.toJson();
	}

	/**
	 * This method adds a new user
	 * 
	 * @param value
	 *            The data represents an user object
	 * @return the result of the operation
	 */
	@POST
	@Consumes(value = {MediaType.APPLICATION_JSON})
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String add(final String value) {
		final User user = JsonObject.parseJson(value, User.class);
		final Result<String> result = new Result<String>();

		return result.toJson();
	}
	/**
	 * This method updates an user by given id
	 * 
	 * @param id
	 *            the id of the user to be updated
	 * @param value
	 *            the user's data
	 * @return the result of the operation
	 */
	@PUT
	@Path("/{id}")
	@Consumes(value = {MediaType.APPLICATION_JSON})
	@Produces(value = {MediaType.APPLICATION_JSON})
	public String update(@PathParam(value = "id") final int id,
			final String value) {
		final User user = JsonObject.parseJson(value, User.class);
		final Result<String> result = new Result<String>();

		return result.toJson();
	}

	/**
	 * This method removes an user from the database
	 * 
	 * @param id
	 *            the id of the user to be deleted
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
