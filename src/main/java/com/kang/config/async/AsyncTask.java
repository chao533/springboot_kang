package com.kang.config.async;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.IdUtil;

/**
　 * <p>Title: AsyncTask</p> 
　 * <p>Description: 异步任务</p> 
　 * @author CK 
　 * @date 2020年4月26日
 */
@Component
public class AsyncTask {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTask.class);


    @Async
	public Future<Map<String,Object>> async1() {
    	logger.info("async1开始执行，当前线程名称【{}】" , Thread.currentThread().getName());
    	
    	Map<String,Object> result = new HashMap<String,Object>();
    	result.put("id", IdUtil.randomUUID());
    	result.put("name", "zhangsan");
    	return new AsyncResult<Map<String,Object>>(result);
	}
	
	@Async
	public Future<Map<String,Object>> async2() {
		logger.info("async2开始执行，当前线程名称【{}】" , Thread.currentThread().getName());
		
		Map<String,Object> result = new HashMap<String,Object>();
    	result.put("id", IdUtil.randomUUID());
    	result.put("name", "lisi");
    	return new AsyncResult<Map<String,Object>>(result);
	}
}
