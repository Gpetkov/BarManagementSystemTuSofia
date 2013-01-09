import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UserDao {
	@PersistenceContext(name = "BarManagementSystem")
	private EntityManager em;

	public void save(TestEntity t) {
		em.persist(t);
	}
}
