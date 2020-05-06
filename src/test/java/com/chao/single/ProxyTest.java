package com.chao.single;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.hutool.core.lang.Console;

public class ProxyTest implements Person{
	

	
	public static void main(String[] args) {
		
		Person per = new ProxyTest();
		
		Person p = (Person)Proxy.newProxyInstance(per.getClass().getClassLoader(), per.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Console.log("before");
				Object obj = method.invoke(per, args);
				Console.log("after");
				return obj;
			}
		});
		p.show();
	}

	@Override
	public void show() {
		Console.log("show");
	}
}

interface Person {
	public void show();
}
