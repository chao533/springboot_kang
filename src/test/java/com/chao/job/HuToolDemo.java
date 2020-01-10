package com.chao.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.szdtoo.model.User;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;

public class HuToolDemo {

	public static void main(String[] args) {
		test6();
	}

	public static void test1() {
		User user1 = new User();
		user1.setAddr("21");
		user1.setId(1l);
		User user2 = new User();
		Console.log(user2);
		BeanUtil.copyProperties(user1, user2);
		Console.log(user2);
	}

	public static void test2() {
		ArrayList<Integer> newArrayList = CollUtil.newArrayList(2,3,4,43,2,1);
		Map<String,Object> param2 = MapUtil.builder(new HashMap<String,Object>()).put("id", 3).put("name", "李四").build();
		Map<String,Object> param1 = MapUtil.builder(new HashMap<String,Object>()).put("id", 2).put("name", "张三").put("user", param2).put("userList", newArrayList).build();
		Map<String,Object> param3 = CollUtil.newHashMap();
		Console.log(param3);
		BeanUtil.copyProperties(param1, param3);
		Console.log(param3);
		
		List<Integer> list = MapUtil.get(param3, "userList", new TypeReference<List<Integer>>(){});
		Console.log(list);
		Map<String, Object> map = MapUtil.get(param3, "user", new TypeReference<Map<String,Object>>(){});
		Console.log(map);
	}

	// 过滤
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

	// 编辑
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

	public static void test6() {
		Map<String,Object> param1 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 1).put("name", "zhangsan").build();
		Map<String,Object> param2 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 2).put("name", "李四").build();
		Map<String,Object> param4 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 2).put("name", "李四").build();
		Map<String,Object> param3 = MapUtil.builder(new LinkedHashMap<String,Object>()).put("id", 3).put("name", "wangwu").build();
		List<Map<String,Object>> list = CollUtil.newArrayList(param3,param1,param2);
		List<Map<String,Object>> list2 = CollUtil.newArrayList(param1);
		Console.log(list);
		Console.log(list2);
//		Collection<Map<String,Object>> all = CollUtil.union(list,list2);
		Collection<Map<String,Object>> all = CollUtil.intersection(list,list2);
		ArrayList<Map<String, Object>> newArrayList = CollUtil.newArrayList(all);
		Console.log(newArrayList);
	}

	public static void test7() {

	}
}
