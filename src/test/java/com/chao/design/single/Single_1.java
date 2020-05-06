package com.chao.design.single;

/**
 * <p>Title: Single_1</p>
 * <p>Description: 单例设计模式的实现方式一<p>
 * @author ChaoKang
 * @date 2020年5月6日
 */
public class Single_1 {

	// 饿汉式
	private static final Single_1 single = new Single_1();
	
	private Single_1() {
		//throw new RuntimeException();
	}
	
	public static Single_1 getInstant() {
		return single;
	}
	
	public static void main(String[] args) {
		System.out.println(getInstant());
		System.out.println(getInstant());
	}
	
	
}

