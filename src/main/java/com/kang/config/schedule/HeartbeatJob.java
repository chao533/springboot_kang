package com.kang.config.schedule;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kang.config.async.AsyncTask;

/**
 * 第一位，表示秒，取值0-59
 * 第二位，表示分，取值0-59
 * 第三位，表示小时，取值0-23
 * 第四位，日期天/日，取值1-31
 * 第五位，日期月份，取值1-12
 * 第六位，星期，取值1-7，1表示星期天，2表示星期一
 * 第七位，年份，可以留空，取值1970-2099
　 * <p>Title: HeartbeatJob</p> 
　 * <p>Description: 定时任务</p> 
　 * @author CK 
　 * @date 2020年4月7日
 */
@Component
public class HeartbeatJob {
	
    private static final Logger logger = LoggerFactory.getLogger(HeartbeatJob.class);
    
    @Autowired
    private AsyncTask asyncTask;

    /**
     *<p>Title: getData</p> 
     *<p>Description: </p>
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    @Scheduled(cron = "0 5 9 * * *")
    public void getData() throws Exception {
    	logger.info("Job开始执行，当前线程名称【{}】" , Thread.currentThread().getName());
    	
    	Map<String,Object> result1 = asyncTask.async1().get();
    	logger.info("result1:{}" ,result1);
		Map<String,Object> result2 = asyncTask.async2().get();
		logger.info("result2:{}" ,result2 + "\n");
    }


}
