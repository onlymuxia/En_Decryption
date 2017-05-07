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
	 * AES�����ַ���
	 * 
	 * @param content
	 *            ��Ҫ�����ܵ��ַ���
	 * @param password
	 *            ������Ҫ������
	 * @return ����
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// ����AES��Key������

			kgen.init(128, new SecureRandom(password.getBytes()));// �����û�������Ϊ�������ʼ����
																	// 128λ��key������
			// ����û��ϵ��SecureRandom�����ɰ�ȫ��������У�password.getBytes()�����ӣ�ֻҪ������ͬ�����о�һ�������Խ���ֻҪ��password����

			SecretKey secretKey = kgen.generateKey();// �����û����룬����һ����Կ

			byte[] enCodeFormat = secretKey.getEncoded();// ���ػ��������ʽ����Կ���������Կ��֧�ֱ��룬�򷵻�
															// null��

			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// ת��ΪAESר����Կ

			Cipher cipher = Cipher.getInstance("AES");// ����������

			byte[] byteContent = content.getBytes("utf-8");

			cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��Ϊ����ģʽ��������

			byte[] result = cipher.doFinal(byteContent);// ����

			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES�����ַ���
	 * 
	 * @param content
	 *            ��Ҫ�����ܵ��ַ���
	 * @param password
	 *            ������Ҫ������
	 * @return ����
	 */
	public static byte[] encrypt( byte[] content, byte[] keys) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);// ����AES��Key������

			kgen.init(128, new SecureRandom(keys));// �����û�������Ϊ�������ʼ����
			// 128λ��key������
			// ����û��ϵ��SecureRandom�����ɰ�ȫ��������У�password.getBytes()�����ӣ�ֻҪ������ͬ�����о�һ�������Խ���ֻҪ��password����

			SecretKey secretKey = kgen.generateKey();// �����û����룬����һ����Կ

			byte[] enCodeFormat = secretKey.getEncoded();// ���ػ��������ʽ����Կ���������Կ��֧�ֱ��룬�򷵻�
			// null��

			SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);// ת��ΪAESר����Կ

			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);// ����������

			cipher.init(Cipher.ENCRYPT_MODE, key);// ��ʼ��Ϊ����ģʽ��������

			byte[] result = cipher.doFinal(content);// ����

			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����AES���ܹ����ַ���
	 * 
	 * @param content
	 *            AES���ܹ���������
	 * @param password
	 *            ����ʱ������
	 * @return ����
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// ����AES��Key������
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();// �����û����룬����һ����Կ
			byte[] enCodeFormat = secretKey.getEncoded();// ���ػ��������ʽ����Կ
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// ת��ΪAESר����Կ
			Cipher cipher = Cipher.getInstance("AES");// ����������
			cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��Ϊ����ģʽ��������
			byte[] result = cipher.doFinal(content);
			return result; // ����

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����AES���ܹ����ַ���
	 * 
	 * @param content
	 *            AES���ܹ���������
	 * @param password
	 *            ����ʱ������
	 * @return ����
	 */
	public static byte[] decrypt(byte[] content, byte[] keys) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// ����AES��Key������
			kgen.init(128, new SecureRandom(keys));
			SecretKey secretKey = kgen.generateKey();// �����û����룬����һ����Կ
			byte[] enCodeFormat = secretKey.getEncoded();// ���ػ��������ʽ����Կ
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// ת��ΪAESר����Կ
			Cipher cipher = Cipher.getInstance("AES");// ����������
			cipher.init(Cipher.DECRYPT_MODE, key);// ��ʼ��Ϊ����ģʽ��������
			byte[] result = cipher.doFinal(content);
			return result; // ����

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/*
	   * ����
	   * 1.������Կ������
	   * 2.����ecnodeRules�����ʼ����Կ������
	   * 3.������Կ
	   * 4.�����ͳ�ʼ��������
	   * 5.���ݼ���
	   * 6.�����ַ���
	   */
	    public static String AESEncode(String encodeRules,String content){
	        try {
	            //1.������Կ��������ָ��ΪAES�㷨,�����ִ�Сд
	            KeyGenerator keygen=KeyGenerator.getInstance("AES");
	            //2.����ecnodeRules�����ʼ����Կ������
	            //����һ��128λ�����Դ,���ݴ�����ֽ�����
	            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
	              //3.����ԭʼ�Գ���Կ
	            SecretKey original_key=keygen.generateKey();
	              //4.���ԭʼ�Գ���Կ���ֽ�����
	            byte [] raw=original_key.getEncoded();
	            //5.�����ֽ���������AES��Կ
	            SecretKey key=new SecretKeySpec(raw, "AES");
	              //6.����ָ���㷨AES�Գ�������
	            Cipher cipher=Cipher.getInstance("AES");
	              //7.��ʼ������������һ������Ϊ����(Encrypt_mode)���߽��ܽ���(Decrypt_mode)�������ڶ�������Ϊʹ�õ�KEY
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            //8.��ȡ�������ݵ��ֽ�����(����Ҫ����Ϊutf-8)��Ȼ��������������ĺ�Ӣ�Ļ�����ľͻ����Ϊ����
	            byte [] byte_encode=content.getBytes("utf-8");
	            //9.�����������ĳ�ʼ����ʽ--���ܣ������ݼ���
	            byte [] byte_AES=cipher.doFinal(byte_encode);
	          //10.�����ܺ������ת��Ϊ�ַ���
	            //������Base64Encoder�л��Ҳ�����
	            //����취��
	            //����Ŀ��Build path�����Ƴ�JRE System Library������ӿ�JRE System Library�����±�����һ�������ˡ�
	            String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
	          //11.���ַ�������
	            return AES_encode;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        
	        //����д�ͷ���nulll
	        return null;         
	    }
	    /*
	     * ����
	     * ���ܹ��̣�
	     * 1.ͬ����1-4��
	     * 2.�����ܺ���ַ������ĳ�byte[]����
	     * 3.���������ݽ���
	     */
	    public static String AESDncode(String encodeRules,String content){
	        try {
	            //1.������Կ��������ָ��ΪAES�㷨,�����ִ�Сд
	            KeyGenerator keygen=KeyGenerator.getInstance("AES");
	            //2.����ecnodeRules�����ʼ����Կ������
	            //����һ��128λ�����Դ,���ݴ�����ֽ�����
	            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
	              //3.����ԭʼ�Գ���Կ
	            SecretKey original_key=keygen.generateKey();
	              //4.���ԭʼ�Գ���Կ���ֽ�����
	            byte [] raw=original_key.getEncoded();
	            //5.�����ֽ���������AES��Կ
	            SecretKey key=new SecretKeySpec(raw, "AES");
	              //6.����ָ���㷨AES�Գ�������
	            Cipher cipher=Cipher.getInstance("AES");
	              //7.��ʼ������������һ������Ϊ����(Encrypt_mode)���߽���(Decrypt_mode)�������ڶ�������Ϊʹ�õ�KEY
	            cipher.init(Cipher.DECRYPT_MODE, key);
	            //8.�����ܲ����������ݽ�����ֽ�����
	            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
	            /*
	             * ����
	             */
	            byte [] byte_decode=cipher.doFinal(byte_content);
	            String AES_decode=new String(byte_decode,"utf-8");
	            return AES_decode;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	        
	        //����д�ͷ���nulll
	        return null;         
	    }
	public static void main(String[] args) {
/*		String content = "��Ů��Լ��";
		String password = "123";
		System.out.println("����֮ǰ��" + content);

		// ����
		byte[] encrypt = encrypt(content, password);
		System.out.println("���ܺ�����ݣ�" + new String(encrypt));

		// ����
		byte[] decrypt = decrypt(encrypt, password);
		System.out.println("���ܺ�����ݣ�" + new String(decrypt));*/
		
		
		
		byte[] original = HexUtil.hexStr2ByteArr("01");
		byte[] keys = HexUtil.hexStr2ByteArr("00112233445566778899AABBCCDDEEFF");
		
		
		// ����
		byte[] encrypt = encrypt(original, keys);
		System.out.println("���ܺ�����ݣ�" + HexUtil.byteArr2HexStr(encrypt));

		// ����
		byte[] decrypt = decrypt(encrypt, keys);
		System.out.println("���ܺ�����ݣ�" + HexUtil.byteArr2HexStr(decrypt));
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////////
//			SymmetricEncoder se=new SymmetricEncoder();
//	        Scanner scanner=new Scanner(System.in);
	        /*
	         * ����
	         */
	        System.out.println("ʹ��AES�ԳƼ��ܣ���������ܵĹ���");
	        String encodeRules="12";
	        System.out.println("������Ҫ���ܵ�����:");
	        String content = "12";
	        System.out.println("��������Ĺ���"+encodeRules+"���ܺ��������:"+AESEncode(encodeRules, content));
	       
	        /*
	         * ����
	         */
	        System.out.println("ʹ��AES�Գƽ��ܣ���������ܵĹ���(���������ͬ)");
	         encodeRules="12";
	        System.out.println("������Ҫ���ܵ����ݣ����ģ�:");
	         content = "7C1Hn7JgMIhhbPd1ke6eaQ==";
	        System.out.println("��������Ĺ���"+encodeRules+"���ܺ��������:"+AESDncode(encodeRules, content));
	}

}
