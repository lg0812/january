package com.Jan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ChengLongProxy {

	public ChengLongProxy() {
		// TODO Auto-generated constructor stub
	}

	private Person chenglong = new Chenglong();

	public Person getProxy() {
		return (Person) Proxy.newProxyInstance(ChengLongProxy.class.getClassLoader(),
				chenglong.getClass().getInterfaces(), new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// TODO Auto-generated method stub
						System.out.println("method---->" + method.getName() + "  args--->" + args);
						return method.invoke(proxy, args);
					}
				});
	}
}
