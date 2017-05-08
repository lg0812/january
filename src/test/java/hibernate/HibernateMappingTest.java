package hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import model.Name;
import model.Person;
import model.Phone;
import model.PhoneType;
import model.Product;

/**
 * Created by hsx on 2017/3/31.
 */
public class HibernateMappingTest {
	private static SessionFactory getSessionFactory() {
		Configuration conf = new Configuration().configure();
		return conf.buildSessionFactory(
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry());
	}

	@Test
	public void main() {

		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.createQuery("delete from Phone").executeUpdate();
		session.createQuery("delete from Person").executeUpdate();
		session.createQuery("delete from Product").executeUpdate();

		Person p = new Person();
		p.setName(new Name("first1", "last" + new Date().getTime(), "middle"));
		session.save(p);

		Phone ph = new Phone();
		ph.setNumber("cvbnm1");
		ph.setPerson(p);
		System.out.println(PhoneType.MOBILE);
		ph.setType(PhoneType.MOBILE);
		session.save(ph);

		Person p1 = new Person();
		p1.setName(new Name("first1", "last" + new Date().getTime(), "middle"));
		session.save(p1);

		Phone ph1 = new Phone();
		ph1.setNumber("cvbnm2");
		ph1.setPerson(p1);
		session.save(ph1);

		Product pro = new Product();
		pro.setDescription("这是一段描述！");
		pro.setName("asus");
		pro.setSku("sku");
		session.save(pro);

		List<Phone> list = session.createQuery("from Phone").list();
		System.out.println(JSON.toJSONString(list));
		System.out.println(JSON.toJSONString(list.get(0).getPerson()));

		tx.commit();
		session.close();
	}

	public String getFirst() {
		// TODO Auto-generated method stub
		System.out.println("-----");
		StringBuilder s = new StringBuilder("");
		for (Long i = 0L; i < Math.pow(2, 20); i++) {
			s = s.append(
					"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		}
		System.out.println(s);
		return s.toString();
	}

//	@Test
//	public void test_get_one() {
//		Session session = this.getSessionFactory().openSession();
//		Transaction tx = session.beginTransaction();
//
//		Person person = (Person) session.createQuery("from Person where id = 1").uniqueResult();
//		System.out.println(JSON.toJSONString(person.getPhones()));
//		tx.commit();
//		session.close();
//	}
//
//	@Test
//	public void test_get_many() {
//		Session session = this.getSessionFactory().openSession();
//		Transaction tx = session.beginTransaction();
//
//		Phone phone = (Phone) session.createQuery("from Phone where id = 8").uniqueResult();
//		System.out.println(JSON.toJSONString(phone));
//		System.out.println(JSON.toJSONString(phone.getPerson()));
//		tx.commit();
//		session.close();
//	}

	@Test
	public void test_del() {
		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.createQuery("delete from Phone where id = 23").executeUpdate();

		tx.commit();
		session.close();
	}

	@Test
	public void test_del_() {
		Session session = this.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.createQuery("delete from Person where id = 7").executeUpdate();

		tx.commit();
		session.close();
	}

	@Test
	public void getEnumValue() {
		System.out.println(PhoneType.LAND_LINE);
	}
}
