package com.Jan.Aspect;

import java.lang.reflect.Modifier;

import org.aspectj.lang.JoinPoint;

public class CacheInterceptor {

	public CacheInterceptor() {
		// TODO Auto-generated constructor stub
	}

	public void notCache(JoinPoint jp) {
		System.out.println("目标方法名为:" + jp.getSignature().getName());
		System.out.println("目标方法所属类的简单类名:" + jp.getSignature().getDeclaringType().getSimpleName());
		System.out.println("目标方法所属类的类名:" + jp.getSignature().getDeclaringTypeName());
		System.out.println("目标方法声明类型:" + Modifier.toString(jp.getSignature().getModifiers()));
		// 获取传入目标方法的参数
		Object[] args = jp.getArgs();
		for (int i = 0; i < args.length; i++) {
			System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
		}
		System.out.println("被代理的对象:" + jp.getTarget());
		System.out.println("代理对象自己:" + jp.getThis());
		System.out.println("notCache");
	}
}
