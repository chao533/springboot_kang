package com.kang.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.kang.common.msg.Message;

/**
 * <p>Title: ResponseUtils</p>  
 * <p>Description: 响应数据格式工具类</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
public class ResponseUtils {
	
	private static Logger log = LoggerFactory.getLogger(ResponseUtils.class);
	
	public static void responseResult(HttpServletResponse response, Message<String> message) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        PrintWriter out = null;
        try {
            //response.reset();
            out = response.getWriter();
            out.print(JSON.toJSONString(message));
            out.flush();
        } catch (IOException e) {
            log.error("responseResult error:{}", e.getMessage());
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }
}
