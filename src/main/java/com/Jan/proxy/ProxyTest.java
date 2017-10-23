package com.Jan.proxy;

import org.junit.Test;

public class ProxyTest {
	@Test
	public void t() {
		ChengLongProxy p = new ChengLongProxy();
		Person ps = p.getProxy();
		ps.sing("hello");
		ps.dance("world");
	}
}
