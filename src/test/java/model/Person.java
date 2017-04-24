package model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hsx on 2017/3/31.
 */
@Entity(name = "Person")
public class Person {

	@Id
	@Column(columnDefinition = "char(32)")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	@Column
	private Name name;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new Date();

	public Name getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private List<Phone> phones = new ArrayList<Phone>();

	public Person() {
	}

	public Person(String id) {
		this.id = id;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void addPhone(Phone phone) {
		phones.add(phone);
		phone.setPerson(this);
	}

	public void removePhone(Phone phone) {
		phones.remove(phone);
		phone.setPerson(null);
	}
}
