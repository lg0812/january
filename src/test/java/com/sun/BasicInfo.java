package com.sun;

import java.util.Arrays;

public class BasicInfo {

	public static void main(String[] args) {
		Arrays.asList("Larry", "Moe", "Curly").forEach(e -> {
			System.out.println(e);
		});
	}
}
