package com.chao.hutool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.Data;
@Data
public class HuToolTest_2 {

	public static void main(String[] args) {

//		test2();
//		test1();
//		test3();
		test4();
//		log.info("用户{}", 1);
//		test12();
	}
	
	// 取出Map对象的复制属性并循环操作
	public static void test12() {
		HashMap<Object, Object> newHashMap = CollUtil.newHashMap();
		Map<String, Object> user1 = MapUtil.builder(new HashMap<String,Object>()).put("id", 1).put("name", "sz").build();
		Map<String, Object> user2 = MapUtil.builder(new HashMap<String,Object>()).put("id", 2).put("name", "li").build();
		List<Map<String, Object>> list = CollUtil.newArrayList(user1,user2);
		newHashMap.put("userList", list);
		newHashMap.put("schoolId", 219);
		Console.log(newHashMap);
		
		List<?> list1 = MapUtil.get(newHashMap, "userList", List.class);
		list1.forEach(ele -> {
			Console.log(ele);
		});
		
		List<Map<String, Object>> list2 = MapUtil.get(newHashMap, "userList", new TypeReference<List<Map<String, Object>>>(){});
		list2.forEach(ele -> {
			Console.log(ele);
		});
		
	}
	
	// 复制对象的去重操作
	public static void test11() {
		Map<String, Object> user1 = MapUtil.builder(new HashMap<String,Object>()).put("id", 1).put("name", "sz").build();
		Map<String, Object> user2 = MapUtil.builder(new HashMap<String,Object>()).put("id", 2).put("name", "li").build();
		List<Map<String, Object>> list = CollUtil.newArrayList(user1,user2);
		System.out.println(list);
		
		Map<String, Object> user3 = MapUtil.builder(new HashMap<String,Object>()).put("id", 3).put("name", "li2").build();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> newList = (List<Map<String, Object>>)CollUtil.removeAny(list, user3);
		System.out.println(newList);
	}
	
	// 排序
	public static void test10() {
		ArrayList<Integer> list = CollUtil.newArrayList(2,3,4,10,3,8);
		System.out.println(list);
//		CollUtil.sort(list, (o1,o2) -> o1 > o2 ? 1 : -1);
		CollUtil.sort(list, (o1,o2) -> o2.compareTo(o1));
		System.out.println(list);
		
		list.forEach(test -> {
			System.out.println(test);
		});
		
	}
	
	public static void test9() {
		Map<String, Object> user1 = MapUtil.builder(new HashMap<String,Object>()).put("id", 1).put("name", "sz").build();
		Map<String, Object> user2 = MapUtil.builder(new HashMap<String,Object>()).put("id", 1).put("name", "sz").build();
//		Set<Map<String, Object>> list = CollUtil.newHashSet(user1,user2);
		List<Map<String, Object>> list = CollUtil.newArrayList(user1,user2);
		
		System.out.println(list);
		System.out.println(CollUtil.distinct(list));
//		List<Integer> list1 = CollUtil.newArrayList(3,4,5,5,3,2);
//		
//		List<Integer> list2 = CollUtil.newArrayList(1,2,3,4,5,6);
//		System.out.println(CollUtil.distinct(list1));
//		System.out.println(CollUtil.disjunction(list1, list2));
	}
	
//	public static void test8() {
//		Map<String,Object> params = MapUtil.builder(new String(), new Object()).put("name", "zk").build();
//		Map<String,Object> params2 = MapUtil.builder(new HashMap<String,Object>()).put("name", "zk").build();
//	}
	
	// 文件操作
	public static void test7() {
		String extName = FileUtil.extName(new File("G:/fsdkl/1.gif")); // 取出文件扩展名
		System.out.println(extName);
		
		System.out.println(FileUtil.getUserHomeDir()); 
		System.out.println(FileUtil.getUserHomePath());
		System.out.println(FileUtil.getTmpDir()); // 临时目录
		System.out.println(FileUtil.mainName(new File("G:/1.gif"))); // 主文件名（不含扩展名）
	}
	
	// 数组切割
	public static void test6() {
		List<Integer> list = CollUtil.newArrayList(2,3,4,5,6);
		String join = CollUtil.join(list, ",");
		System.out.println(join);
		Integer[] arr = ArrayUtil.append(new Integer[]{}, 2,3,3,4,5);
		String join2 = ArrayUtil.join(arr, ",");
		System.out.println(join2);
	}
	
	// 图片操作
	public static void test5() {
		ImgUtil.convert(new File("G:/1.gif"), new File("G:/3.PNG")); // 转换图片格式
	}
	
	
    // 创建线程
	public static void test1() {
		new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
	}
	// 创建线程
	public static void test2() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		}).start();
	}
	
	// 过滤
	public static void test3() {
		List<Integer> list = CollUtil.newArrayList(1,2,3,4);
		Console.log(list);
		List<Integer> list2 = CollUtil.filter(list, new Filter<Integer>() {
			@Override
			public boolean accept(Integer t) {
				
				return t == 2 ? false:true;
			}
		});
		CollUtil.filter(list, (Integer t) -> t == 2 ? false:true);
		Console.log(list2);
	}
	// 循环
	public static void test4() {
		ArrayList<Integer> newArrayList = CollUtil.newArrayList(1,2,3,4);
		
		newArrayList.forEach(value -> System.out.println(value));
		
		List<Integer> subList = newArrayList.stream().filter(t -> t == 2 ? false : true).distinct().collect(Collectors.toList());
		Console.log(subList);
		
	}
	
}
