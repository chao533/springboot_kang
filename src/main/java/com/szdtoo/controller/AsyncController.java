package com.szdtoo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.config.async.AsyncTask;

@RequestMapping("/async")
@RestController
public class AsyncController {

	@Autowired
	private AsyncTask asyncTask;
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public Message<?> get() throws Exception{
		System.out.println("main....." +  Thread.currentThread().getName());
		Map<String,Object> result1 = (Map<String, Object>) asyncTask.async1().get();
		Map<String,Object> result2 = (Map<String, Object>) asyncTask.async2().get();
		Map<String,Object> result3 = new HashMap<String,Object>();
		result3.put("result1", result1);
		result3.put("result2", result2);
		return new Message<Map<String,Object>>(ErrorCode.SUCCESS,result3);
	}
		
	
}
