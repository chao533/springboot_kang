package com.chao.hutool;

import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.kang.common.constant.PropConstants;

import cn.hutool.core.codec.Base32;
import cn.hutool.core.codec.Base62;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.EscapeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;

public class HuToolTest_4 {
	
	private static final String str = "这是一段字符串Abc123_~!@#$%^&*()+://";

	
	public static void main(String[] args) {
		test9();
	}
	
	
	/**
	 *<p>Title: test1</p> 
	 *<p>Description: escape和URL编码和解码</p>
	 */
	public static void test1() {
		String escape = EscapeUtil.escape(str);
		Console.log("escape编码后：{}",escape);
		String unescape = EscapeUtil.unescape(escape);
		Console.log("escape解码后：{}", unescape);
		System.out.println();
		String encode = URLUtil.encode(str,CharsetUtil.CHARSET_UTF_8);
		Console.log("URL编码后：{}",encode);
		String decode = URLUtil.decode(encode, CharsetUtil.CHARSET_UTF_8);
		Console.log("URL解码后：{}",decode);
	}
	
	
	/**
	 *<p>Title: test2</p> 
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
	 *<p>Title: test3</p> 
	 *<p>Description: AES对称加密解密</p>
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
	 *<p>Title: test4</p> 
	 *<p>Description: DES对称加密解密</p>
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
	
	/**
	 *<p>Title: test5</p> 
	 *<p>Description: RSA非对称加密</p>
	 */
	public static void test5() {
		KeyPair pair = SecureUtil.generateKeyPair("RSA");
		PrivateKey generatePrivateKey = pair.getPrivate();
		Console.log("privateKey:{}",generatePrivateKey.getEncoded());
		PublicKey generatePublicKey = pair.getPublic();
		Console.log("publicKey:{}",generatePublicKey.getEncoded());
		
		RSA rsa = new RSA(generatePrivateKey,generatePublicKey);
		
		String encryptHex = rsa.encryptHex(str, CharsetUtil.CHARSET_UTF_8, KeyType.PublicKey);
		Console.log("rsa加密后:{}",encryptHex);
		String decryptStr = rsa.decryptStr(encryptHex, KeyType.PrivateKey, CharsetUtil.CHARSET_UTF_8);
		Console.log("rsa解密后:{}",decryptStr);
	}
	
	/**
	 *<p>Title: test6</p> 
	 *<p>Description: 生成公钥和密钥，并写入磁盘</p>
	 */
	public static void test6() {
		KeyPair pair = SecureUtil.generateKeyPair("RSA");
		PrivateKey generatePrivateKey = pair.getPrivate();
		Console.log("privateKey:{}",generatePrivateKey.getEncoded());
		PublicKey generatePublicKey = pair.getPublic();
		Console.log("publicKey:{}",generatePublicKey.getEncoded());
		FileUtil.writeBytes(generatePrivateKey.getEncoded(), new File("c://config/privateKey.txt"));
		FileUtil.writeBytes(generatePublicKey.getEncoded(), new File("c://config/publicKey.txt"));
	}
	
	/**
	 *<p>Title: test7</p> 
	 *<p>Description: 读取classpath下的公钥和密钥</p>
	 */
	public static void test7() {
//		InputStream in1 = HuToolTest_4.class.getClassLoader().getResourceAsStream("config/privateKey.txt");
//		InputStream in2 = HuToolTest_4.class.getClassLoader().getResourceAsStream("config/publicKey.txt");
		
		byte[] privateKeyBytes = ResourceUtil.readBytes(PropConstants.PRIVATE_KEY);
		byte[] publicKeyBytes = ResourceUtil.readBytes(PropConstants.PUBLIC_KEY);
		RSA rsa = new RSA(privateKeyBytes,publicKeyBytes);
		String encryptHex = rsa.encryptHex(str, CharsetUtil.CHARSET_UTF_8, KeyType.PublicKey);
		Console.log("rsa加密后:{}",encryptHex);
		String decryptStr = rsa.decryptStr(encryptHex, KeyType.PrivateKey, CharsetUtil.CHARSET_UTF_8);
		Console.log("rsa解密后:{}",decryptStr);
	}
	
	/**
	 *<p>Title: test8</p> 
	 *<p>Description: 摘要算法</p>
	 */
	public static void test8() {
		String md5 = SecureUtil.md5(str);
		Console.log("md5加密后:{}",md5);
		
		String sha1 = SecureUtil.sha1(str);
		Console.log("sha1加密后:{}",sha1);
		
		String sha256 = SecureUtil.sha256(str);
		Console.log("sha256加密后:{}",sha256);
		
		String sha256Hex = DigestUtil.sha256Hex(str);
		Console.log("sha256加密后:{}",sha256Hex);
	}
	
	/**
	 *<p>Title: test9</p> 
	 *<p>Description: 签名和验证</p>
	 */
	public static void test9() {
		byte[] data = str.getBytes();
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withRSA);
		//签名
		byte[] signed = sign.sign(data);
		//验证签名
		boolean verify = sign.verify(data, signed);
		Console.log("验证签名:{}",verify);
	}
	
	
}
