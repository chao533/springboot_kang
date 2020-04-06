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
 * AsyncDemo
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/25
 */
@Component
public class AsyncTask {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTask.class);


    @Async
	public Future<Map<String,Object>> async1() throws InterruptedException {
    	logger.info("task1....." + Thread.currentThread().getName());
    	Map<String,Object> result = new HashMap<String,Object>();
    	result.put("id", IdUtil.randomUUID());
    	result.put("name", "zhangsan");
    	return new AsyncResult<Map<String,Object>>(result);
	}
	
	@Async
	public Future<Map<String,Object>> async2() throws InterruptedException {
		logger.info("task2....." + Thread.currentThread().getName());
		Map<String,Object> result = new HashMap<String,Object>();
    	result.put("id", IdUtil.randomUUID());
    	result.put("name", "lisi");
    	return new AsyncResult<Map<String,Object>>(result);
	}
}
