package com.szdtoo.common.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.szdtoo.common.msg.ErrorCode;
import com.szdtoo.common.msg.Message;

/**
 * <p>Title: DefaultExceptionHandler</p>  
 * <p>Description: 统一异常处理类</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
@RestControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    public Message<String> processServiceException(ServiceException e) {
        LOGGER.error("业务异常:{}",e.getMessage());
        return new Message<String>(ErrorCode.ERROR,e.getMessage());
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public Message<String> processIllegalArgumentException(IllegalArgumentException e) {
        LOGGER.error("参数异常:{}",e.getMessage());
        return new Message<>(ErrorCode.ERROR,e.getMessage());
    }
    
    @ExceptionHandler(AuthenticationException.class)
    public Message<String> processAuthenticationException(AuthenticationException e) {
        LOGGER.error("登录异常:{}",e.getMessage());
        return new Message<>(ErrorCode.ERROR,e.getMessage());
    }
    
    
    @ExceptionHandler(TokenValidationException.class)
    public Message<String> processServiceException(TokenValidationException e) {
        LOGGER.error("Token认证异常:{}",e.getMessage());
        return new Message<String>(ErrorCode.ERROR,e.getMessage());
    }

}
