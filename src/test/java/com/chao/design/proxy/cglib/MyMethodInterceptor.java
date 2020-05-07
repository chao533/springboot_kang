package com.chao.design.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import cn.hutool.core.lang.Console;
/**
　 * <p>Title: MyMethodInterceptor</p> 
　 * <p>Description: Cglib动态代理，实现MethodInterceptor接口</p> 
　 * @author CK 
　 * @date 2020年5月7日
 */
public class MyMethodInterceptor implements MethodInterceptor{
	
	@Override
	public Object intercept(Object obj, Method method, Object[] params, MethodProxy proxy) throws Throwable {
		before();
		Object res = proxy.invokeSuper(obj, params);
		after();
		return res;
	}
	
	/**
	 *<p>Title: getProxyObj</p> 
	 *<p>Description: 获取代理对象</p> 
	 * @param target
	 * @return
	 */
	public Object getProxyObj(Object target) {
		Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);// 设置回调 
        Object result = enhancer.create();//创建并返回代理对象
        return result;
	}
	
	public void before() {
		Console.log("目标方法之前执行...");
	}
	
	public void after() {
		Console.log("目标方法之前执行...");
	}

}
