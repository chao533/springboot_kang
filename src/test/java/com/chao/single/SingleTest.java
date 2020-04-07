package com.chao.single;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import cn.hutool.core.lang.Console;

public class SingleTest {

	public static void main(String[] args) throws Exception{
		test1();
	}

	// 单例
	public static void test1() {
		Single s1 = Single.getInstall();
		Single s2 = Single.getInstall();
		Console.log(s2);
		Console.log(s1);
		Console.log(s1 == s2);
	}

	// 序列化反序列化变多例
	public static void test2() throws Exception {
		// 序列化
		Single s1 = Single.getInstall();
		Console.log(s1);
        FileOutputStream fo = new FileOutputStream("c:/1.txt");
        ObjectOutputStream so = new ObjectOutputStream(fo);
        so.writeObject(s1);
        so.close();
        
        // 反序列化
        FileInputStream fi = new FileInputStream("c:/1.txt");
        ObjectInputStream si = new ObjectInputStream(fi);
        Single s2 = (Single) si.readObject();
        Console.log(s2);
        si.close();
        
        Console.log(s1 ==  s2);
        
	}
	
	// 饿汉单例变多例
	public static void test3() throws Exception {
		Class<?> forName = Class.forName("com.chao.hutool.Single");
		Constructor<?> constructor = forName.getDeclaredConstructor();
		constructor.setAccessible(true);
		Single s1 = (Single)constructor.newInstance();
		Single s2 = (Single)constructor.newInstance();
		System.out.println(s1);
		System.out.println(s2);
		Console.log(s1 ==  s2);
	}
	
	// 懒汉单例变多例
	public static void test4() throws Exception {
		Class<?> forName = Class.forName("com.chao.hutool.Singleton");
		Constructor<?> constructor = forName.getDeclaredConstructor();
		constructor.setAccessible(true);
		Singleton s1 = (Singleton)constructor.newInstance();
		Console.log(s1);
		
		Field field=s1.getClass().getDeclaredField("isFristCreate");
		field.setAccessible(true);
		field.set(s1, true);
		Singleton s2 = (Singleton)constructor.newInstance();
		Console.log(s2);
		
		
	}
}
