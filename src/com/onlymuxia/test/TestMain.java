package com.onlymuxia.test;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestMain {

	static final String KEY_ALGORITHM = "AES";  
	
	/**
	 * AES加密字符串
	 * 
	 * @param content
	 *            需要被加密的字符串
	 * @param password
	 *            加密需要的密码
	 * @return 密文
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者

			kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
																	// 128位的key生产者
			// 加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
															// null。

			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

			Cipher cipher = Cipher.getInstance("AES");// 创建密码器

			byte[] byteContent = content.getBytes("utf-8");

			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

			byte[] result = cipher.doFinal(byteContent);// 加密

			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES加密字符串
	 * 
	 * @param content
	 *            需要被加密的字符串
	 * @param password
	 *            加密需要的密码
	 * @return 密文
	 */
	public static byte[] encrypt( byte[] content, byte[] keys) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);// 创建AES的Key生产者

			kgen.init(128, new SecureRandom(keys));// 利用用户密码作为随机数初始化出
			// 128位的key生产者
			// 加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
			// null。

			SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);// 转换为AES专用密钥

			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);// 创建密码器

			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

			byte[] result = cipher.doFinal(content);// 加密

			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密AES加密过的字符串
	 * 
	 * @param content
	 *            AES加密过过的内容
	 * @param password
	 *            加密时的密码
	 * @return 明文
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
			byte[] result = cipher.doFinal(content);
			return result; // 明文

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密AES加密过的字符串
	 * 
	 * @param content
	 *            AES加密过过的内容
	 * @param password
	 *            加密时的密码
	 * @return 明文
	 */
	public static byte[] decrypt(byte[] content, byte[] keys) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
			kgen.init(128, new SecureRandom(keys));
			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
			byte[] result = cipher.doFinal(content);
			return result; // 明文

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/*
	   * 加密
	   * 1.构造密钥生成器
	   * 2.根据ecnodeRules规则初始化密钥生成器
	   * 3.产生密钥
	   * 4.创建和初始化密码器
	   * 5.内容加密
	   * 6.返回字符串
	   */
	    public static String AESEncode(String encodeRules,String content){
	        try {
	            //1.构造密钥生成器，指定为AES算法,不区分大小写
	            KeyGenerator keygen=KeyGenerator.getInstance("AES");
	            //2.根据ecnodeRules规则初始化密钥生成器
	            //生成一个128位的随机源,根据传入的字节数组
	            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
	              //3.产生原始对称密钥
	            SecretKey original_key=keygen.generateKey();
	              //4.获得原始对称密钥的字节数组
	            byte [] raw=original_key.getEncoded();
	            //5.根据字节数组生成AES密钥
	            SecretKey key=new SecretKeySpec(raw, "AES");
	              //6.根据指定算法AES自成密码器
	            Cipher cipher=Cipher.getInstance("AES");
	              //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
	            byte [] byte_encode=content.getBytes("utf-8");
	            //9.根据密码器的初始化方式--加密：将数据加密
	            byte [] byte_AES=cipher.doFinal(byte_encode);
	          //10.将加密后的数据转换为字符串
	            //这里用Base64Encoder中会找不到包
	            //解决办法：
	            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
	            String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
	          //11.将字符串返回
	            return AES_encode;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        
	        //如果有错就返加nulll
	        return null;         
	    }
	    /*
	     * 解密
	     * 解密过程：
	     * 1.同加密1-4步
	     * 2.将加密后的字符串反纺成byte[]数组
	     * 3.将加密内容解密
	     */
	    public static String AESDncode(String encodeRules,String content){
	        try {
	            //1.构造密钥生成器，指定为AES算法,不区分大小写
	            KeyGenerator keygen=KeyGenerator.getInstance("AES");
	            //2.根据ecnodeRules规则初始化密钥生成器
	            //生成一个128位的随机源,根据传入的字节数组
	            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
	              //3.产生原始对称密钥
	            SecretKey original_key=keygen.generateKey();
	              //4.获得原始对称密钥的字节数组
	            byte [] raw=original_key.getEncoded();
	            //5.根据字节数组生成AES密钥
	            SecretKey key=new SecretKeySpec(raw, "AES");
	              //6.根据指定算法AES自成密码器
	            Cipher cipher=Cipher.getInstance("AES");
	              //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
	            cipher.init(Cipher.DECRYPT_MODE, key);
	            //8.将加密并编码后的内容解码成字节数组
	            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
	            /*
	             * 解密
	             */
	            byte [] byte_decode=cipher.doFinal(byte_content);
	            String AES_decode=new String(byte_decode,"utf-8");
	            return AES_decode;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        
	        //如果有错就返加nulll
	        return null;         
	    }
	public static void main(String[] args) {
/*		String content = "美女，约吗？";
		String password = "123";
		System.out.println("加密之前：" + content);

		// 加密
		byte[] encrypt = encrypt(content, password);
		System.out.println("加密后的内容：" + new String(encrypt));

		// 解密
		byte[] decrypt = decrypt(encrypt, password);
		System.out.println("解密后的内容：" + new String(decrypt));*/
		
		
		
		byte[] original = HexUtil.hexStr2ByteArr("01");
		byte[] keys = HexUtil.hexStr2ByteArr("00112233445566778899AABBCCDDEEFF");
		
		
		// 加密
		byte[] encrypt = encrypt(original, keys);
		System.out.println("加密后的内容：" + HexUtil.byteArr2HexStr(encrypt));

		// 解密
		byte[] decrypt = decrypt(encrypt, keys);
		System.out.println("解密后的内容：" + HexUtil.byteArr2HexStr(decrypt));
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////////
//			SymmetricEncoder se=new SymmetricEncoder();
//	        Scanner scanner=new Scanner(System.in);
	        /*
	         * 加密
	         */
	        System.out.println("使用AES对称加密，请输入加密的规则");
	        String encodeRules="12";
	        System.out.println("请输入要加密的内容:");
	        String content = "12";
	        System.out.println("根据输入的规则"+encodeRules+"加密后的密文是:"+AESEncode(encodeRules, content));
	       
	        /*
	         * 解密
	         */
	        System.out.println("使用AES对称解密，请输入加密的规则：(须与加密相同)");
	         encodeRules="12";
	        System.out.println("请输入要解密的内容（密文）:");
	         content = "7C1Hn7JgMIhhbPd1ke6eaQ==";
	        System.out.println("根据输入的规则"+encodeRules+"解密后的明文是:"+AESDncode(encodeRules, content));
	}

}
