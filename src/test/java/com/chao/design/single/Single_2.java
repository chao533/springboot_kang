package com.chao.design.single;

/**
 * <p>Title: Single_2</p>
 * <p>Description: 单例模式实现方式二<p>
 * @author ChaoKang
 * @date 2020年5月6日
 */
public class Single_2 {

	// 懒汉式
	private static Single_2 single = null;
	
	private Single_2() {
		//throw new RuntimeException();
	}
	// 解决线程安全问题
	public static Single_2 getInstant() {
		if(single == null) {
			synchronized (Single_2.class) {
				if(single == null) {
					single = new Single_2();
					return single;
				}
			}
		}
		return single;
	}
	
	public static void main(String[] args) {
		System.out.println(getInstant());
		System.out.println(getInstant());
	}
}
