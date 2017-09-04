package com.Jan.Thread;

public class Test1 {
	public static void main(String[] args) {
		MyObject obj = new MyObject();
		ThreadA a = new ThreadA(obj);
		ThreadB b = new ThreadB(obj);
		a.start();
		b.start();
	}
}

class MyObject {
	public static int sum;

	synchronized public void MethodA() {
		for (int t = 0; t < 5; t++) {
			sum = 5-t;
			System.out.println(sum);
		}
	}

	synchronized public void MethodB() {
		for (int t = 0; t < 5; t++) {
			sum = t;
			System.out.println(sum);
		}
	}
}

class ThreadA extends Thread {
	private MyObject obj;

	public ThreadA(MyObject obj2) {
		// TODO Auto-generated constructor stub
		this.obj = obj2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		obj.MethodA();
	}

}

class ThreadB extends Thread {
	private MyObject obj;

	public ThreadB(MyObject obj2) {
		// TODO Auto-generated constructor stub
		this.obj = obj2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		obj.MethodB();
	}

}