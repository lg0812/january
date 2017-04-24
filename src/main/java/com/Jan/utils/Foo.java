package com.Jan.utils;

public class Foo {
	public void listen(String foo) throws InterruptedException {

		System.out.println(foo);
		new Thread().sleep(1000);
		System.out.println("------------------end-------------------");
	}
}
