package com.Jan.java8;

public class Apple {

	public Apple() {
		// TODO Auto-generated constructor stub
	}

	public Apple(long id, String color, double weight) {
		super();
		this.id = id;
		this.color = color;
		this.weight = weight;
	}

	public Apple(double weight) {
		super();
		this.weight = weight;
	}

	public long id;
	public String color;
	public double weight;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
