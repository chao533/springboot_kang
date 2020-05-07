package com.chao.design.proxy.cglib;

import com.chao.design.proxy.StuService;
import com.chao.design.proxy.StuServiceImpl;

/**
　 * <p>Title: CglibTest</p> 
　 * <p>Description: 动态代理是在程序运行时根据需要动态创建目标类的代理对象 2</p> 
　 * @author CK 
　 * @date 2020年5月7日
 */
public class CglibTest {

	
	public static void main(String[] args) {
		// 创建目标对象
		StuService stuService = new StuServiceImpl();
		stuService.getStuInfo();
		
		System.out.println();
		
		MyMethodInterceptor methodInterceptor = new MyMethodInterceptor();
		StuService cglibStuService = (StuService)methodInterceptor.getProxyObj(stuService);
		cglibStuService.getStuInfo();
	}
}
