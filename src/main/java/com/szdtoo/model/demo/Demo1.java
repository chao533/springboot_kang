package com.szdtoo.model.demo;

import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;

public class Demo1 {

	
	public static void main(String[] args) throws Exception {
//		ThreadLocal<String> local = new ThreadLocal<>();
//		local.set("123");
//		String string = local.get();
//		System.out.println(Thread.currentThread().getName());
		test2();
	}
	
	private static ThreadLocal<SimpleDateFormat> format1 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			// TODO Auto-generated method stub
			return super.initialValue();
		}
	};
	
	public void test() {
		new Callable<String>() {

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	public static void test2() throws Exception {
		Callable<String> call = () -> "21";
		String s = call.call();
		System.out.println(s);
	}
	
}
