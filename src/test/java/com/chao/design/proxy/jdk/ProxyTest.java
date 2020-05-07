package com.chao.design.proxy.jdk;

import com.chao.design.proxy.StuService;
import com.chao.design.proxy.StuServiceImpl;

/**
　 * <p>Title: ProxyTest</p> 
　 * <p>Description: 动态代理是在程序运行时根据需要动态创建目标类的代理对象</p> 
　 * @author CK 
　 * @date 2020年5月7日
 */
public class ProxyTest {
	
	public static void main(String[] args) {
		// 创建目标对象
		StuService stuService = new StuServiceImpl();
		stuService.getStuInfo();
		
		System.out.println();
		
		// 创建目标类的代理对象
		MyInvocationHandler handler = new MyInvocationHandler();
		StuService proxyStuService = (StuService)handler.getProxyObj(stuService);
		proxyStuService.getStuInfo();
	}
}
