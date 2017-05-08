package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

//表示可被引用
@Embeddable
public class Name {
	@Type(type = "text")
	public String first;
	public String last;
	public String middle;

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public Name() {
		super();
	}

	public Name(String first, String last, String middle) {
		super();
		this.first = first;
		this.last = last;
		this.middle = middle;
	}
}
