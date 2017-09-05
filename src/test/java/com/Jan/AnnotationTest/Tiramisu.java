package com.Jan.AnnotationTest;

@Factory(id = "Tiramisu", type = Meal.class)
public class Tiramisu implements Meal {

	public Tiramisu() {
		// TODO Auto-generated constructor stub
	}

	public float getPrice() {
		return 4.5f;
	}

	public String getName() {
		return "Tiramisu";
	}

}
