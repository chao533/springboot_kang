package com.chao.hutool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

public class HuToolTest_5 {

	
	public static void main(String[] args) {
		test3();
	}
	
	/**
	 *<p>Title: test1</p> 
	 *<p>Description: 测试登录接口</p>
	 */
	public static void test1() {
		String url = "http://192.168.0.206/spad-login/common/userLogin";
		Map<String,Object> paramMap = MapUtil.builder(new HashMap<String,Object>()).put("username", "king").put("password", "123456").build();
		String body = JSONUtil.toJsonStr(paramMap);
		String res = HttpUtil.post(url, body);
		Console.log(res);
	}
	
	/**
	 *<p>Title: test2</p> 
	 *<p>Description: 测试含请求头的接口</p>
	 */
	public static void test2() {
		HttpResponse httpResponse = HttpUtil.createPost("http://192.168.0.206/spad-base/pad/center/getStuUserInfo")
			.header("Content-Type", "application/json")
			.header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJjeGZAc3R1Iiwic2NvcGUiOlsic2VydmljZSJdLCJyb2xlTmFtZSI6MSwiaWQiOjksImV4cCI6MTU4OTYyNzA5MSwianRpIjoiMzZkMmIwNDUtZmFiNC00YmE5LTk5MTUtNzNiOWNhZWM0MGFmIiwiY2xpZW50X2lkIjoidXNlci1zZXJ2aWNlIiwidXNlcm5hbWUiOiJjeGZAc3R1In0.T-tMywS6ATJSlqVMZgWhG2uOFNCIPTxfKzy_aiBUeXTHG2uOf52pGT0CMoPAJba8Vrz1VqJ3bilpqroqeoh_kw-JKbrPCRHOArZlIJJnV5CVJj9Jc4QkJeGEp9hiKW4UKNFK9XkZrxWXARoPAVUSeSu04Qx0RZu3v7_25MUfPsEgdMSVw9IHbWI8CQGoktpa9_bCpfVoVTFsF3FMmIcbJ_3Xr2zfdD91rlPUES0UhG3R-Yr3nKOf3Thi-bvq38oFC-RBjbApONQkyfz6q9DroF9tWAACoTASwVc1IirtnoWHrOq1z6hpjiEPOdPVCLzyBU1dnGWGbt9_pU9_OynW-w")
			.execute();
		Console.log(httpResponse.body());
	}
	
	/**
	 *<p>Title: test3</p> 
	 *<p>Description: json转Map</p>
	 */
	public static void test3() {
		Map<String,Object> params = Dict.create().set("username", "king").set("password", "123456");
		Console.log("params:{}", params);
		String jsonStr = JSONUtil.toJsonStr(params);
		Console.log("jsonStr:{}", jsonStr);
//		Map<?,?> bean = JSONUtil.toBean(jsonStr, Map.class);
		
		Map<String,Object> bean = JSONUtil.toBean(jsonStr, new TypeReference<Map<String,Object>>() {}, false);
		Console.log("bean:{}", bean);
	}
	
	/**
	 *<p>Title: test4</p> 
	 *<p>Description: 测试登录</p>
	 */
	public static void test4() {
		String url = "http://192.168.0.206/spad-login/common/userLogin";
//		Map<String,Object> paramMap = MapUtil.builder(new HashMap<String,Object>()).put("username", "king").put("password", "123456").build();
		Map<String,Object> paramMap = Dict.create().set("username", "king").set("password", "123456");
		String body = JSONUtil.toJsonStr(paramMap);
//		String res = HttpUtil.post(url, body);
//		Console.log(res);
		
		HttpResponse httpResponse = HttpRequest.post(url)
			.contentType("application/json")
			.body(body)
			.execute();
		
		Console.log(httpResponse.body());
		Map<String,Object> bodyMap = JSONUtil.toBean(httpResponse.body(), new TypeReference<Map<String,Object>>() {}, true);
		Console.log(bodyMap);
	}
	
	/**
	 *<p>Title: test5</p> 
	 *<p>Description: 内存分页</p>
	 */
	public static void test5() {
		Map<String,Object> result = CollUtil.newHashMap();
		List<String> data1 = CollUtil.newArrayList("1","2","3","4","5","6","7","8","9","10");
		List<String> data2 = CollUtil.newArrayList("11","22","33","44","55");
		List<String> data = CollUtil.newArrayList();
		data.addAll(data1);
		data.addAll(data2);
		CollUtil.sort(data, (o1,o2) -> Integer.parseInt(o2) - Integer.parseInt(o1));
		List<String> page = CollUtil.page(1, 3, data);
		result.put("total", data.size());
		result.put("data", page);
		result.put("code", 200);
		result.put("msg", "操作成功");
		Console.log(JSONUtil.toJsonStr(result));
	}
	
	/**
	 *<p>Title: test6</p> 
	 *<p>Description: Map的封装</p>
	 */
	public static void test6() {
		Dict dict = Dict.create().set("username", "king").set("password", "123456");
		String username = dict.getStr("username");
		Console.log("username:{}",username);
	}
	
	public static void test7() {
		CronUtil.schedule("* * * * * *", new Task() {
			
			@Override
			public void execute() {
				Console.log(Thread.currentThread().getName());
			}
		});
		// 支持秒级别定时任务
		CronUtil.setMatchSecond(true);
		CronUtil.start();
	}
	
}
