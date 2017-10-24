package com.Jan.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor {
	private Object targetObj;

	public CGLibProxy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] arg2, MethodProxy methodProxy) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println(method.getName());
		return method.invoke(targetObj, arg2);
	}

}
