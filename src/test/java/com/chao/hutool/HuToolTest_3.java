package com.chao.hutool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.chao.hutool.model.Goods;
import com.chao.hutool.model.Person;
import com.chao.hutool.model.User;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;

public class HuToolTest_3 {

	public static void main(String[] args) {
		test11();
	}

	public static void test1() {
		User user1 = User.builder().id(1l).username("zs").build();
		User user2 = User.builder().build();
		Console.log(user2);
		BeanUtil.copyProperties(user1, user2);
		Console.log(user2);
	}
	// 测试对象的深度复制
	public static void test11() {
		Person person = Person.builder().age(12).name("张三").sex(false).build();
		
		User user = User.builder().id(0l).username("liu@dfh.com").pwd("123").build();
		person.setUser(user);
		
		Goods goods1 = Goods.builder().id(1l).goodsName("商品1").build();
		Goods goods2 = Goods.builder().id(1l).goodsName("商品2").build();
		List<Goods> goodsList = CollUtil.newArrayList(goods1,goods2);
		person.setGoodsList(goodsList);
		Console.log(person);
		
		Map<String,Object> params = CollUtil.newHashMap();
		Console.log(params);
		BeanUtil.copyProperties(person, params);
		Console.log(params);
		
		Boolean sex = MapUtil.getBool(params, "sex");
		Console.log(sex);
		Map<String,Object> userMap = MapUtil.get(params, "user", new TypeReference<Map<String,Object>>(){});
		Console.log(userMap);
		List<Map<String,Object>> goodsMapList = MapUtil.get(params, "goodsList", new TypeReference<List<Map<String,Object>>>(){});
		Console.log(goodsMapList);
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
		List<Map<String,Object>> list = CollUtil.newArrayList(param4,param3,param1,param2);
		List<Map<String,Object>> list2 = CollUtil.newArrayList(param1);
		Console.log(list);
		Console.log(list2);
//		Collection<Map<String,Object>> all = CollUtil.union(list,list2);
		Collection<Map<String,Object>> all = CollUtil.intersection(list,list2);
		ArrayList<Map<String, Object>> newArrayList = CollUtil.newArrayList(all);
		Console.log(newArrayList);
	}

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
	
	public static void test8() {
		String[] arr = ArrayUtil.append(new String[] {}, "21","43","54","76","12","73");
		Console.log(arr);
		Integer[] arr2 = Convert.toIntArray(arr);
		Console.log(arr2);
	}
	
	public static void test9() {
		ArrayList<Integer> list = CollUtil.newArrayList(4,5,1,2,8,5,7);
		Console.log(list);
		List<Integer> subList = list.stream().distinct().filter(value -> !new Integer(3).equals(value)).sorted((o1,o2) -> o1.compareTo(o2)).collect(Collectors.toList());
		Console.log(subList);
		
	}

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
