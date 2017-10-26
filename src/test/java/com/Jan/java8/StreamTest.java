package com.Jan.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import javax.print.attribute.standard.PrinterLocation;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class StreamTest {

	public StreamTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 流的构造和转换
	 */
	// @Test
	public void createStream() {
		// 1
		Stream<String> stream = Stream.of("a", "b", "c");
		stream.forEach(t -> System.out.println(t));

		// 2
		String[] arr = new String[] { "arr1", "arr2", "arr3" };
		stream = Stream.of(arr);
		stream.forEach(System.out::println);
		// 3
		List<String> list = Arrays.asList(arr);
		stream = list.stream();
		stream.forEach(System.out::println);

		IntStream.of(new int[] { 1, 2, 3 }).forEach(System.out::println);
		LongStream.of(new long[] { 1L, 2L, 3L }).forEach(System.out::println);
		DoubleStream.of(new double[] { 1, 2, 3 }).forEach(System.out::println);
	}

	/**
	 * 1 Intermediate：</br>
	 * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、
	 * parallel、 sequential、 unordered </br>
	 * 
	 * 2 Terminal： </br>
	 * forEach、forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、
	 * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator </br>
	 * 
	 * 3 Short-circuiting：</br>
	 * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
	 */
	@Test
	public void streamToOthers() {
		int[] arr = IntStream.range(1, 10).toArray();
		List<String> wordList = new ArrayList<>();
		wordList.add("abc");
		wordList.add("bcd");
		wordList.add("cde");

		long start = Calendar.getInstance().getTimeInMillis();
		System.out.println(Calendar.getInstance().getTimeInMillis() - start);
		start = Calendar.getInstance().getTimeInMillis();
		for (String str : wordList) {
			System.out.println(str.toUpperCase());
		}
		System.out.println(Calendar.getInstance().getTimeInMillis() - start);
		start = Calendar.getInstance().getTimeInMillis();
		wordList.parallelStream().forEach((t) -> System.out.println(t.toUpperCase()));
		System.out.println(Calendar.getInstance().getTimeInMillis() - start);
	}

	public <T> List<T> findApple(List<T> list, ApplePredicate<T> p) {
		List<T> result = new ArrayList<T>();
		for (T apple : list) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	@Test
	public void selectApple() {
		List<Apple> result = new ArrayList<Apple>();
		result.add(new Apple(1, "red", 120));
		result.add(new Apple(2, "red", 160));
		result.add(new Apple(3, "green", 140));
		result.add(new Apple(4, "red", 170));
		result.add(new Apple(5, "green", 190));
		List<Apple> redApples = findApple(result, (Apple apple) -> "red".equals(apple.getColor()));
		for (Apple a : redApples)
			System.out.println(a.id);

	}

	public <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<T>();
		for (T apple : list) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	/**
	 * @FunctionalInterface java.util.function.Consumer boolean test(T t);
	 * @FunctionalInterface java.util.function.Predicate void accept(T t);
	 * @FunctionalInterface java.util.function.Function apply(T t);
	 */
	public void selectApplePredicate() {
		Predicate<Apple> notEmpty = (Apple s) -> s.getColor().isEmpty();

		List<Apple> result = new ArrayList<Apple>();
		result.add(new Apple(1, "red", 120));
		result.add(new Apple(2, "red", 160));
		result.add(new Apple(3, "green", 140));
		result.add(new Apple(4, "red", 170));
		result.add(new Apple(5, "green", 190));
		List<Apple> redApples = filter(result, notEmpty);
		for (Apple a : redApples)
			System.out.println(a.id);

	}

	/**
	 * 构造函数引用 </br>
	 * 
	 */
	// @Test
	public void construct() {
		Supplier<Apple> t = Apple::new;
		Apple a = t.get();

		// 等价于：Lambda表达式
		Supplier<Apple> tt = () -> new Apple();
		Apple aa = tt.get();

		Function<Double, Apple> c2 = Apple::new;
		Apple a2 = c2.apply(100.0);

		System.out.println(a2.getWeight());
		List<Double> weights = Arrays.asList(7., 3., 4., 10.);
		List<Apple> apples = map(weights, Apple::new);
		System.out.println(JSON.toJSONString(apples));

		// Comparator<Apple> c = (Apple app1, Apple app2) -> {
		// return (int) (app1.getWeight() - app2.getWeight());
		// };
		apples.sort((app1, app2) -> (int) (app1.getWeight() - app2.getWeight()));

	}

	public static List<Apple> map(List<Double> list, Function<Double, Apple> f) {
		List<Apple> result = new ArrayList<>();
		for (Double e : list) {
			result.add(f.apply(e));
		}
		return result;
	}
}
