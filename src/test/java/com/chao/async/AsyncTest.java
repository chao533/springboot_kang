package com.chao.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chao.BaseTest;
import com.kang.config.async.TaskFactory;

import lombok.SneakyThrows;

public class AsyncTest extends BaseTest{
	
	private static final Logger log = LoggerFactory.getLogger(AsyncTest.class);

	@Autowired
    private TaskFactory task;

    /**
     *<p>Title: asyncTaskTest</p> 
     *<p>Description: 测试异步任务</p> 
     * @throws InterruptedException
     * @throws ExecutionException
     */
	@Test
	@SneakyThrows
    public void asyncTaskTest(){
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
     *<p>Title: taskTest</p> 
     *<p>Description: 测试同步任务</p> 
     * @throws InterruptedException
     */
	@Test
	@SneakyThrows
    public void taskTest() {
        long start = System.currentTimeMillis();
        task.task1();
        task.task2();
        task.task3();
        long end = System.currentTimeMillis();

        log.info("同步任务全部执行结束，总耗时：{} 毫秒", (end - start));
    }
}
