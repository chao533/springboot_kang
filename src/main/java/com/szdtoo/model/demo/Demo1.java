package com.szdtoo.model.demo;

import java.text.SimpleDateFormat;

public class Demo1 {

	
	public static void main(String[] args) throws Exception {
		ThreadLocal<String> local = new ThreadLocal<>();
		local.set("123");
		String string = local.get();
		System.out.println(Thread.currentThread().getName());
	}
	
	private static ThreadLocal<SimpleDateFormat> format1 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			// TODO Auto-generated method stub
			return super.initialValue();
		}
	};
	
}
