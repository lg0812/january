package com.Jan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MathProxy implements InvocationHandler {
	public Object proxyObj;

	public MathProxy() {
		// TODO Auto-generated constructor stub

	}

	public Object getProxyObject(Object obj) {
		this.proxyObj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("before method>>>>>***>>>>>>>" + method.getName());
		return method.invoke(proxyObj, args);
	}

}
