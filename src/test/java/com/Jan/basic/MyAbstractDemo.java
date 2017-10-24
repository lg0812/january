package com.Jan.basic;

/**
 * 如果一个类继承于一个抽象类，则子类必须实现父类的抽象方法。 <br/>
 * 如果子类没有实现父类的抽象方法，则必须将子类也定义为为abstract类。
 */
public abstract class MyAbstractDemo {
	public String str;

	/**
	 * 抽象类可以有构造方法(0个到多个)，在子类中可以通过super(args)来调用 <br/>
	 * 例如：super();
	 */
	public MyAbstractDemo() {
		// TODO Auto-generated constructor stub
	}

	// 抽象方法默认为public
	public abstract String getUser(String username);

}
