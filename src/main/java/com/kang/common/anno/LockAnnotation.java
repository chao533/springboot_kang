package com.kang.common.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
　 * <p>Title: LockAnnotation</p> 
　 * <p>Description: 同步锁注解</p> 
　 * @author CK 
　 * @date 2020年4月15日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockAnnotation {
	
	String value() default "";
}
