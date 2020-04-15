package com.kang.common.anno;
import java.lang.annotation.*; 
/**
　 * <p>Title: Servicelock</p> 
　 * <p>Description: 自定义注解 同步锁</p> 
　 * @author CK 
　 * @date 2020年4月15日
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented    
public  @interface Servicelock { 
	 String description()  default "";
}
