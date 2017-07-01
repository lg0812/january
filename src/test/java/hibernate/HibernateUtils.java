package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {
	public static SessionFactory getSessionFactory() {
		// hibernate5
		// return new MetadataSources(new
		// StandardServiceRegistryBuilder().configure().build()).buildMetadata().buildSessionFactory();
		// hibernate4
		Configuration conf = new Configuration().configure();
		return conf.buildSessionFactory(
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry());
	}
}
