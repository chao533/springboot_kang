package com.chao.hutool;

import cn.hutool.core.codec.Base32;
import cn.hutool.core.codec.Base62;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;

public class HuToolTest_4 {
	
	private static final String str = "这是一段字符串Abc123_~!@#$%^&*()+://";

	
	public static void main(String[] args) {
		test4();
	}
	
	
	/**
	 *<p>Title: test5</p> 
	 *<p>Description: escape和URL编码和解码</p>
	 */
	public static void test1() {
		String escape = EscapeUtil.escape(str);
		Console.log("编码后：{}",escape);
		String unescape = EscapeUtil.unescape(escape);
		Console.log("解码后：{}", unescape);
		System.out.println();
		String encode = URLUtil.encode(str,CharsetUtil.CHARSET_UTF_8);
		Console.log("编码后：{}",encode);
		String decode = URLUtil.decode(encode, CharsetUtil.CHARSET_UTF_8);
		Console.log("解码后：{}",decode);
	}
	
	
	/**
	 *<p>Title: test8</p> 
	 *<p>Description: Base64 32 62编码解码</p>
	 */
	public static void test2() {
		
		String encode62 = Base62.encode(str, CharsetUtil.CHARSET_UTF_8);
		Console.log("Base62编码后:{}",encode62);
		String decodeStr62 = Base62.decodeStr(encode62, CharsetUtil.CHARSET_UTF_8);
		Console.log("Base62解码后:{}",decodeStr62);
		System.out.println("----------------");
		
		String encode64 = Base64.encode(str, CharsetUtil.CHARSET_UTF_8);
		Console.log("Base64编码后:{}",encode64);
		String decodeStr64 = Base64.decodeStr(encode64, CharsetUtil.CHARSET_UTF_8);
		Console.log("Base64解码后:{}",decodeStr64);
		System.out.println("----------------");
		
		String encode32 = Base32.encode(str, CharsetUtil.CHARSET_UTF_8);
		Console.log("Base32编码后:{}",encode32);
		String decodeStr32 = Base32.decodeStr(encode32, CharsetUtil.CHARSET_UTF_8);
		Console.log("Base32解码后:{}",decodeStr32);
		System.out.println("----------------");
		
	}
	/**
	 *<p>Title: test9</p> 
	 *<p>Description: AES加密解密</p>
	 */
	public static void test3() {
		// key必须为16位
		String key = RandomUtil.randomString(16);
		Console.log("key:{}",key);
		//随机生成密钥
		AES aes = SecureUtil.aes(key.getBytes());
		//加密
//		byte[] encrypt = aes.encrypt(content);
		//解密
//		byte[] decrypt = aes.decrypt(encrypt);
		//加密为16进制表示
		String encryptHex = aes.encryptHex(str, CharsetUtil.CHARSET_UTF_8);
		Console.log("aes加密后:{}",encryptHex);
		//解密为字符串
		String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
		Console.log("aes解密后:{}",decryptStr);
	}
	
	/**
	 *<p>Title: test10</p> 
	 *<p>Description: DES加密解密</p>
	 */
	public static void test4() {
		// key位数不限
		String key = RandomUtil.randomString(100);
		Console.log("key:{}",key);
		//随机生成密钥
		DES des = SecureUtil.des(key.getBytes());
		
		String encryptHex = des.encryptHex(str, CharsetUtil.CHARSET_UTF_8);
		Console.log("des加密后:{}",encryptHex);
		//解密为字符串
		String decryptStr = des.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
		Console.log("des解密后:{}",decryptStr);
	}
	
	
	
}
