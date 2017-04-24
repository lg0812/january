package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateMappingTest1 {
	@Test
	public void test_01() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		tx.commit();
		session.close();
	}
}
