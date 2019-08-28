package com.szdtoo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;
import com.szdtoo.config.async.AsyncTask;
import com.szdtoo.config.async.TaskFactory;

@RequestMapping("/async")
@RestController
public class AsyncController {
	private static final Logger log = LoggerFactory.getLogger(AsyncController.class);

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
	
	@Autowired
    private TaskFactory task;

    /**
     * 测试异步任务
     */
	@RequestMapping(value="/test1",method=RequestMethod.GET)
    public void asyncTaskTest() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        Future<Boolean> asyncTask1 = task.asyncTask1();
        Future<Boolean> asyncTask2 = task.asyncTask2();
        Future<Boolean> asyncTask3 = task.asyncTask3();

        // 调用 get() 阻塞主线程
        asyncTask1.get();
        asyncTask2.get();
        asyncTask3.get();
        long end = System.currentTimeMillis();

        log.info("异步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }

    /**
     * 测试同步任务
     */
	@RequestMapping(value="/test2",method=RequestMethod.GET)
    public void taskTest() throws InterruptedException {
        long start = System.currentTimeMillis();
        task.task1();
        task.task2();
        task.task3();
        long end = System.currentTimeMillis();

        log.info("同步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }
		
	
}
