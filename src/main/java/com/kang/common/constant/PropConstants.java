package com.kang.common.constant;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

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

	public static String getProp(String file, String key) {
		try {
			return new PropertiesConfiguration(file).getString(key);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
