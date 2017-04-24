package model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

/**
 * Created by hsx on 2017/3/31.
 */
@Entity(name = "Phone")
public class Phone {

	@Id
	// @Column(columnDefinition = "char(32)")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	@NaturalId
	@Column(name = "`number`", unique = true)
	private String number;

	@ManyToOne
	private Person person;
	// 使用枚举数据
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "phone_type")
	private PhoneType type;

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}

	public Phone() {
	}


	public void setNumber(String number) {
		this.number = number;
	}

	public Phone(String number) {
		this.number = number;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Phone phone = (Phone) o;
		return Objects.equals(number, phone.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}