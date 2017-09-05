package com.Jan.AnnotationTest;

@Factory(id = "Calzone", type = Meal.class)
public class CalzonePizza implements Meal {

	public CalzonePizza() {
		// TODO Auto-generated constructor stub
	}

	public float getPrice() {
		return 8.5f;
	}

	public String getName() {
		return "Calzone Pizza";
	}

}
