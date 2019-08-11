package com.chao.job;

import java.util.Map;
import java.util.Set;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;

public class TestJob {

	public static void main(String[] args) {
		
		Set<Map<String,Object>> newArrayList = CollUtil.newHashSet();
		
		Map<String,Object> map1 = MapUtil.createMap(Object.class);
		
		map1.put("id", 1);
		map1.put("name", "zhangsan");
		map1.put("sex", true);
		newArrayList.add(map1);
		Map<String,Object> map2 = MapUtil.newHashMap();
		
		map2.put("id", 1);
		map2.put("name", "zhangsan");
		map2.put("sex", true);
		newArrayList.add(map1);
		System.out.println(newArrayList);
		
		if(CollUtil.isNotEmpty(newArrayList)) {
			System.out.println(11);
		}
	}
}
