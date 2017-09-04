package com.Jan.Thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class SingleThread {

	public SingleThread() {
		// TODO Auto-generated constructor stub

	}

	@Test
	public void testSingle() {
		ExecutorService pool = Executors.newSingleThreadExecutor();
		Thread t1 = new MyThread("t1");
		Thread t2 = new MyThread("t2");
		Thread t3 = new MyThread("t3");
		Thread t4 = new MyThread("t4");

		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.shutdown();
	}

}

class MyThread extends Thread {
	public String str;

	public MyThread(String str) {
		this.str = str;

	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + str + "   running...");
	}
}
