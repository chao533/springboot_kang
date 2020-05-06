package com.chao.design.single;

/**
 * <p>Title: Single_3</p>
 * <p>Description: 单例实现方式三<p>
 * @author ChaoKang
 * @date 2020年5月6日
 */
public class Single_3 {

	private Single_3() {
		//throw new RuntimeException();
	}
	
	/**
	 * <p>Title: InnerSingle</p>
	 * <p>Description: 静态内部类实现单例<p>
	 * @author ChaoKang
	 * @date 2020年5月6日
	 */
	static class InnerSingle{
		private static Single_3 single = new Single_3();
	}
	
	public static Single_3 getInstant() {
		return Single_3.InnerSingle.single;
	}
	
	public static void main(String[] args) {
		System.out.println(getInstant());
		System.out.println(getInstant());
		System.out.println(Single_4.SINGLE);
		System.out.println(Single_4.SINGLE);
	}
	
	
}
