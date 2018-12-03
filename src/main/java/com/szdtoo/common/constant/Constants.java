package com.szdtoo.common.constant;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: Constants</p>  
 * <p>Description: 常量配置类</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
public final class Constants {

    private static Logger log = LoggerFactory.getLogger(Constants.class);
    
    /**
     * redis配置文件
     */
    public static String REDISCONFIG = "redis.properties";
    
    /**
     * fdfs配置文件
     */
    public static String FDFSCONFIG = "fdfs.properties";
    
    /**
     * 
     * <p>Title: propertiesKey</p>  
     * <p>Description: properties文件的key</p>  
     * @author chaokang  
     * @date 2018年12月3日
     */
    public interface propertiesKey{
        /**
         * fastdfs访问地址
         */
        String FDFS_WEBSERVER = "fdfs.webserver";
    }
    
    
    /**
     * <p>Title: PayType</p>  
     * <p>Description: 支付方式</p>  
     * @author chaokang  
     * @date 2018年12月3日
     */
    public interface PayType{
        /**
         * 现金 
         */
        int MONEY = 0;

        /**
         * 支付宝
         */
        int ALIPAY = 1;

        /**
         * 微信
         */
        int WEIXIN = 2;
    }

    /**
     * <p>Title: propertiesVal</p>  
     * <p>Description: 根据配置文件的名称获取值</p>  
     * @param file
     * @param key
     * @return
     */
    public static String propertiesVal(String file, String key) {
        try {
			return new PropertiesConfiguration(file).getString(key);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
}
