package com.tu.university.barmanagement.resources;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.tu.university.barmanagement.managers.OrderStatusManager;

@Stateless
@Path("item")
public class ItemResource {
	@Context
	private UriInfo context;

	@EJB
	OrderStatusManager em;
	/**
	 * Default constructor.
	 */
	public ItemResource() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retrieves representation of an instance of OrderStatusResource
	 * 
	 * @return an instance of String
	 */
	@GET
	@Produces("application/json")
	public String getJson() {
		// TODO return proper representation object
		throw new UnsupportedOperationException();
	}

	/**
	 * PUT method for updating or creating an instance of OrderStatusResource
	 * 
	 * @param content
	 *            representation for the resource
	 * @return an HTTP response with content of the updated or created resource.
	 */
	@PUT
	@Consumes("application/json")
	public void putJson(String content) {
		throw new UnsupportedOperationException();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addOrderStatus(String value) {
		// final Result<OrderStatus> result1 = new Result<OrderStatus>();
		// OrderStatus os = new OrderStatus();
		// os.setValue("dobre");
		// result1.setData(os);
		// final OrderStatus resOrderStatus = JsonObject.parseJson(value,
		// OrderStatus.class);
		// final Result<OrderStatus> result2 = new Result<OrderStatus>();
		// result2.setData(resOrderStatus);
		// em.saveOrderStatus(os);
		// return result2.toJson();
		return "";
	}
}
