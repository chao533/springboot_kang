package com.chao.hutool;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

public class HuToolTest_4 {

	
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
	
	public static void test3() {
		String param = "{\"username\":\"king\",\"password\":\"123456\"}";
//		Map<?,?> bean = JSONUtil.toBean(param, Map.class);
		
		Map<String,Object> bean = JSONUtil.toBean(param, new TypeReference<Map<String,Object>>() {}, false);
		System.out.println("bean:" + bean);
	}
	
	public static void test4() {
		String url = "http://192.168.0.206/spad-login/common/userLogin";
		Map<String,Object> paramMap = MapUtil.builder(new HashMap<String,Object>()).put("username", "king").put("password", "123456").build();
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
}
