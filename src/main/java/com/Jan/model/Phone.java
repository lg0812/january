package com.Jan.model;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Phone {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	@Column
	private String phone_number;
	@Column
	private boolean phone_type;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public boolean isPhone_type() {
		return phone_type;
	}

	public void setPhone_type(boolean phone_type) {
		this.phone_type = phone_type;
	}
}
