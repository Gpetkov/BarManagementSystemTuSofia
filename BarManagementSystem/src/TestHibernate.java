
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestHibernate {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("BarManagementSystem");
		EntityManager orderStatusManager = emf.createEntityManager();

		TestEntity t = new TestEntity();

		orderStatusManager.persist(t);
	}
}
