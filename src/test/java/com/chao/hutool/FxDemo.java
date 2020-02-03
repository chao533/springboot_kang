package com.chao.hutool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 泛型
 * 
 * @author Administrator
 *
 */
@Slf4j
public class FxDemo {

	public static void main(String[] args) {
		test3();
		Triple<Integer, Integer, Integer> triple = new ImmutableTriple<>(1,2,3);
//		Integer i=0;
//		ArrayList<Integer> newArrayList = CollUtil.newArrayList(121,2,3);
//		IntSummaryStatistics collect = newArrayList.stream().collect(Collectors.summarizingInt(ele -> ele));
//		Console.log(collect.getMax());
	}
	public static void test3() {
		Multimap<String, Integer> map = ArrayListMultimap.create();
		map.put("key1", 1);
		map.put("key1", 2);
		// [1, 2]
		System.out.println(map.get("key1")); 
	}
	
	
	public static void test2() {
		List<Integer> newArrayList = CollUtil.newArrayList(121,2,3);
		System.out.println(newArrayList);
		newArrayList = newArrayList.stream().filter(ele -> new Integer("2").equals(ele) ? false : true).collect(Collectors.toList());
		System.out.println(newArrayList);
	}

	public static void test1() {
		ArrayList<Person> newArrayList = CollUtil.newArrayList(new Person());
		
		List<String> list = newArrayList.stream().map(ele -> ele.getName()).collect(Collectors.toList());
		
	}
	

}


class Person {
	private String name;
	
	public String getName() {
		return name;
	}
}



