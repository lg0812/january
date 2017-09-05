package com.Jan.AnnotationTest;

@Factory(id = "Margherita", type = Meal.class)
public class MargheritaPizza implements Meal {

	public MargheritaPizza() {
		// TODO Auto-generated constructor stub
	}

	public float getPrice() {
		return 6f;
	}

	public String getName() {
		return "Margherita Pizza";
	}
}
