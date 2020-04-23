package com.kang.common.constant;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import cn.hutool.setting.Setting;
import cn.hutool.setting.dialect.Props;
import lombok.SneakyThrows;

public class PropConstants {

	/**
	 * jwt配置信息
	 */
	public static final String JWT_PROP = "config/jwt.properties";

	public interface jwtKey {
		
		String EXPIRATION_TIME = "jwt.expiration.time";

		String SECRET = "jwt.secret";

		String TOKEN_PREFIX = "jwt.token.prefix";

		String HEADER_STRING = "jwt.header.string";

		String ROLE = "jwt.role";
	}
	/**
	 * 私钥
	 */
	public static final String PRIVATE_KEY = "config/privateKey.txt";
	
	/**
	 * 公钥
	 */
	public static final String PUBLIC_KEY = "config/publicKey.txt";
	
	
	@SneakyThrows(value= ConfigurationException.class)
	public static String getProp_1(String file, String key) {
		return new PropertiesConfiguration(file).getString(key);
	}
	
	public static String getProp_2(String file, String key) {
		Setting setting = new Setting(JWT_PROP);
		return setting.get(key);
	}
	
	public static String getProp_3(String file, String key) {
		Props props = new Props(JWT_PROP);
		return props.getProperty(key);
	}
}
