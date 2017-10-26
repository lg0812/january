package com.Jan.java8;

@FunctionalInterface
public interface ApplePredicate<T> {
	boolean test(T apple);
}
