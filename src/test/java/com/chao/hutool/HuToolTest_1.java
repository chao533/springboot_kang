package com.chao.hutool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import com.chao.hutool.model.User;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;

public class HuToolTest_1 {

	public static void main(String[] args) {
		test6();
	}
	
	// 对象拷贝
	public static void test1() {
		User user1 = User.builder().id(1l).username("zs").build();
		User user2 = User.builder().build();
		Console.log(user2);
		BeanUtil.copyProperties(user1, user2);
		Console.log(user2);
	}

	// 取出Map对象封装的各种类型属性
	public static void test2() {
		ArrayList<Integer> newArrayList = CollUtil.newArrayList(2,3,4,43,2,1);
		Map<String,Object> param2 = MapUtil.builder(new HashMap<String,Object>()).put("id", 3).put("name", "李四").build();
		Map<String,Object> param1 = MapUtil.builder(new HashMap<String,Object>()).put("id", 2).put("name", "张三").put("user", param2).put("userList", newArrayList).build();
		Map<String,Object> param3 = CollUtil.newHashMap();
		Console.log(param3);
		BeanUtil.copyProperties(param1, param3);
		Console.log(param3);
		// 取出List集合
		List<Integer> list = MapUtil.get(param3, "userList", new TypeReference<List<Integer>>(){});
		Console.log(list);
		// 取出User对象
		Map<String, Object> map = MapUtil.get(param3, "user", new TypeReference<Map<String,Object>>(){});
		Console.log(map);
	}

	// 过滤lambda写法
	public static void test3() {
		Map<String,Object> param1 = MapUtil.builder(new HashMap<String,Object>()).put("id", 1).put("name", "zhangsan").build();
		Map<String,Object> param2 = MapUtil.builder(new HashMap<String,Object>()).put("id", 2).put("name", "李四").build();
		Map<String,Object> param3 = MapUtil.builder(new HashMap<String,Object>()).put("id", 3).put("name", "wangwu").build();
		List<Map<String,Object>> list = CollUtil.newArrayList(param1,param2,param3);
		Console.log(list);
//		list = CollUtil.filter(list, new Filter<Map<String,Object>>() {
//
//			@Override
//			public boolean accept(Map<String, Object> t) {
//				
//				return MapUtil.getInt(t, "id") == 2 ? false : true;
//			}
//		});
		list = CollUtil.filter(list, (Map<String,Object> t) -> MapUtil.getInt(t, "id") == 2 ? false : true);
		Console.log(list);
	}

	// 编辑lambda写法
	public static void test4() {
		Map<String,Object> param1 = MapUtil.builder(new HashMap<String,Object>()).put("id", 1).put("name", "zhangsan").build();
		Map<String,Object> param2 = MapUtil.builder(new HashMap<String,Object>()).put("id", 2).put("name", "李四").build();
		Map<String,Object> param3 = MapUtil.builder(new HashMap<String,Object>()).put("id", 3).put("name", "wangwu").build();
		List<Map<String,Object>> list = CollUtil.newArrayList(param1,param2,param3);
		Console.log(list);
//		list = CollUtil.filter(list, new Editor<Map<String,Object>>() {
//
//			@Override
//			public Map<String, Object> edit(Map<String, Object> t) {
//				if(MapUtil.getInt(t, "id") == 2) {
//					t.put("name", "lisi");
//					return t;
//				} else {
//					return t;
//				}
//			}
//		});
		list = CollUtil.filter(list, (Map<String,Object> map) -> {
			if(MapUtil.getInt(map, "id") == 2) {
				map.put("name", "lisi");
				return map;
			} else {
				return map;
			}
		});
		Console.log(list);
	}

	// 排序lambda写法
	public static void test5() {
		Map<String,Object> param1 = MapUtil.builder(new HashMap<String,Object>()).put("id", 1).put("name", "zhangsan").build();
		Map<String,Object> param2 = MapUtil.builder(new HashMap<String,Object>()).put("id", 2).put("name", "李四").build();
		Map<String,Object> param3 = MapUtil.builder(new HashMap<String,Object>()).put("id", 3).put("name", "wangwu").build();
		List<Map<String,Object>> list = CollUtil.newArrayList(param3,param1,param2);
		Console.log(list);
//		CollUtil.sort(list, new Comparator<Map<String,Object>>() {
//
//			@Override
//			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
//				return MapUtil.getInt(o1, "id").compareTo(MapUtil.getInt(o2, "id"));
//			}
//		});
		CollUtil.sort(list, (o1, o2) -> MapUtil.getInt(o1, "id").compareTo(MapUtil.getInt(o2, "id")));
		Console.log(list);
	}

	// 集合交、并、差集操作
	public static void test6() {
		Map<String,Object> param1 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 1).put("name", "zhangsan").build();
		Map<String,Object> param2 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 2).put("name", "李四").build();
		Map<String,Object> param3 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 3).put("name", "wangwu").build();
		Map<String,Object> param4 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 2).put("name", "李四").build();
		List<Map<String,Object>> list = CollUtil.newArrayList(param4,param3,param2,param1);
		List<Map<String,Object>> list2 = CollUtil.newArrayList(param2);
		Console.log(list);
		Console.log(list2);
		
//		Collection retainAll = CollectionUtils.retainAll(list, list2); // 交集
//		Console.log(retainAll);
//		Collection union = CollectionUtils.union(list, list2); // 并集
//		Console.log(union);
		Collection<?> subtract = CollectionUtils.subtract(list, list2);// 差集
		Console.log(subtract);
		
//		Collection<Map<String,Object>> all = CollUtil.union(list,list2);
//		Collection<Map<String,Object>> all = CollUtil.intersection(list,list2);
//		ArrayList<Map<String, Object>> newArrayList = CollUtil.newArrayList(all);
//		Console.log(newArrayList);
	}

	// for循环的集中方式
	public static void test7() {
		List<Integer> list = CollUtil.newArrayList(1,2,3,4);
		
		for(int i=0; i<list.size(); i++) {
			Console.log(i);	
		}
		
		for(Integer i : list) {
			Console.log(i);
		}
		
		list.forEach(i -> Console.log(i));
	}
	
	// 数组的类型转换(包含元素)
	public static void test8() {
		String[] arr = ArrayUtil.append(new String[] {}, "21","43","54","76","12","73");
		Console.log(arr);
		Integer[] arr2 = Convert.toIntArray(arr);
		Console.log(arr2);
	}
	
	// java8的Stream流写法
	public static void test9() {
		ArrayList<Integer> list = CollUtil.newArrayList(4,5,1,2,8,5,7);
		Console.log(list);
		List<Integer> subList = list.stream().distinct().filter(value -> !new Integer(3).equals(value)).sorted((o1,o2) -> o1.compareTo(o2)).collect(Collectors.toList());
		Console.log(subList);
		list.stream().map((item) -> item > 3).collect(Collectors.toList());
	}

	// java8的Stream流写法（对象操作）
	public static void test10() {
		Map<String,Object> param1 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 1).put("name", "zhangsan").build();
		Map<String,Object> param2 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 2).put("name", "李四").build();
		Map<String,Object> param4 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 2).put("name", "李四").build();
		Map<String,Object> param3 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 3).put("name", "wangwu").build();
		List<Map<String,Object>> list = CollUtil.newArrayList(param3,param1,param2,param4);
		
		Console.log(list);
		List<Integer> list2 = list.stream().distinct().filter(ele -> MapUtil.getInt(ele, "id").equals(1)? false : true).map(ele -> MapUtil.getInt(ele, "id")).collect(Collectors.toList());
		Console.log(list2);
	}
}
