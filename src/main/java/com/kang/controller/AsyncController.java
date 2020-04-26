package com.kang.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kang.common.msg.ErrorCode;
import com.kang.common.msg.Message;
import com.kang.config.async.AsyncTask;

/**
　 * <p>Title: AsyncController</p> 
　 * <p>Description: 异步操作</p> 
　 * @author CK 
　 * @date 2020年4月6日
 */
@RequestMapping("/async")
@RestController
public class AsyncController {
	private static final Logger log = LoggerFactory.getLogger(AsyncController.class);

	@Autowired
	private AsyncTask asyncTask;
	
	/**
	 *<p>Title: get</p> 
	 *<p>Description: 异步获取数据</p> 
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * @throws Exception
	 */
	@RequestMapping(value="/getData",method=RequestMethod.GET)
	public Message<?> get() throws InterruptedException, ExecutionException{
		log.info("main开始执行，当前线程名称【{}】" , Thread.currentThread().getName());
		
		Map<String,Object> result1 = asyncTask.async1().get();
		Map<String,Object> result2 = asyncTask.async2().get();
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("result1", result1);
		result.put("result2", result2);
		return new Message<Map<String,Object>>(ErrorCode.SUCCESS,result);
	}
	
	
}
