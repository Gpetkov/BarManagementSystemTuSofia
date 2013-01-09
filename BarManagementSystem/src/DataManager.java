
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DataManager {
	@PersistenceContext(name = "BarManagementSystem")
	EntityManager em;

	public void createTest(TestEntity t) {
		em.persist(t);
	}
}
