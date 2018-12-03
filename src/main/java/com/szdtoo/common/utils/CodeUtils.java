package com.szdtoo.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * <p>Title: CodeUtils</p>  
 * <p>Description: 编码生成工具类</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
public class CodeUtils {
	
	/**
	 * <p>Title: getRandom</p>  
	 * <p>Description: 产生一个几位的随机数</p>  
	 * @param num
	 * @return
	 */
	public static String getRandom(int num){
		String[] source=new String[]{"0","1","2","3","4","5","6","7","8","9"};
		String result="";
		for(int i=0;i<num;i++){
			Random random = new Random();
	        int index=random.nextInt(10);
	        result+=source[index];
		}
		return result;
	}

	/**
	 * <p>Title: getUUID</p>  
	 * <p>Description: 取得UUID</p>  
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(getRandom(16));
	}
}
