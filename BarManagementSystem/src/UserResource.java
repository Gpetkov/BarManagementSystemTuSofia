

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Stateless
@Path("user")
public class UserResource {
    @Context
    private UriInfo context;
    
    @EJB
	UserDao ud;
    /**
     * Default constructor. 
     */
    public UserResource() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of UserResource
     * @return an instance of String
     */
    
    @GET
    @Produces("application/json")
    public String getJson() {
    	//TestEntity t = new TestEntity();
    	if(ud == null)
    	{
    		return "null";
    	}
    	ud.save(new TestEntity());
    	
       return "za sega raboti";
    }

    /**
     * PUT method for updating or creating an instance of UserResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

}