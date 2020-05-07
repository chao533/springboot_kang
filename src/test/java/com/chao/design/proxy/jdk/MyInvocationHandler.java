package com.chao.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.hutool.core.lang.Console;

/**
　 * <p>Title: MyInvocationHandler</p> 
　 * <p>Description: JDK动态代理，需要实现InvocationHandler接口</p> 
　 * @author CK 
　 * @date 2020年5月7日
 */
public class MyInvocationHandler implements InvocationHandler{
	
	private Object target; // 目标类对象

	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object invoke = method.invoke(target, args);
		after();
		return invoke;
	}
	
	/**
	 *<p>Title: getProxyObj</p> 
	 *<p>Description: 获取目标类的代理对象</p> 
	 * @return
	 */
	public Object getProxyObj(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	public void before() {
		Console.log("目标方法之前执行...");
	}
	
	public void after() {
		Console.log("目标方法之前执行...");
	}
	
	

}
