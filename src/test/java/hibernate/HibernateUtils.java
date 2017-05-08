package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {
	public static SessionFactory getSessionFactory() {
		Configuration conf = new Configuration().configure();
		return conf.buildSessionFactory(
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry());
	}
}
