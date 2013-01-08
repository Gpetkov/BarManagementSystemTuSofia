

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("sample")
public class SimpleService {
	@EJB
	DataManager dm;
	
	@Path("greet")
	@GET
	public String doGreet() {
		dm.createTest(new TestEntity());
		return "Hello Stranger, the time is "+ new Date();
	}
}
