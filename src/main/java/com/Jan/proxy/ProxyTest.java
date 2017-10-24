package com.Jan.proxy;

import org.junit.Test;

import com.Jan.model.User;

public class ProxyTest {
	// @Test
	public void t() {
		ChengLongProxy p = new ChengLongProxy();
		Person ps = p.getProxy();
		ps.sing("hello");
		ps.dance("world");
	}

	@Test
	public void tt() {
		Person str = (Person) new MathProxy().getProxyObject(new Chenglong());
		str.sing("name");
	}
}
