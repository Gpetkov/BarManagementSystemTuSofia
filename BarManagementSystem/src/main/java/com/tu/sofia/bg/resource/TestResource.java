package com.tu.sofia.bg.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(Resource.TEST_PATH)
public class TestResource extends Resource {
	/**
	 * This method returns all users
	 * 
	 */
	@GET
	@Path("/")
	@Produces(value = MediaType.APPLICATION_JSON)
	public void getAllUsers(@PathParam(value = "id") int id) {
		System.out.println(id);
	}

	/**
	 * This method returns a user by Id
	 * 
	 */
	@GET
	@Path("/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public String getUser(@PathParam(value = "id") int id) {
		return "result";
	}
	
	/**
	 * This method adds new User
	 * 
	 * @param value
	 *            the string represents a new User
	 * 
	 */
	@POST
	@Path("/")
	@Consumes(value = { MediaType.TEXT_PLAIN })
	@Produces(value = { MediaType.APPLICATION_JSON })
	public void addUser(String value) {
		System.out.println(value);
	}

	/**
	 * This method update current User
	 * 
	 * @param value
	 *            the string represents the current User
	 * 
	 */
	@PUT
	@Path("/")
	@Consumes(value = { MediaType.TEXT_PLAIN })
	@Produces(value = { MediaType.APPLICATION_JSON })
	public void updateUser(String value) {
		System.out.println(value);
	}
}
