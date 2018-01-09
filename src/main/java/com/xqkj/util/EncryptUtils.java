package com.xqkj.util;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;

public class EncryptUtils {
	/** 字符串默认键值     */
	private static String strDefaultKey = "national";

	/** 加密工具     */
	private static Cipher encryptCipher = null;

	/** 解密工具     */
	private static Cipher decryptCipher = null;
	public static String getMD5(String text) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(text.getBytes());
			return bytes2Hex(md5.digest());
		} catch (Exception ex) {
		}
		return text;
	}

	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des.toUpperCase();
	}

	public static String getSha256(String text) {
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			sha256.update(text.getBytes());
			return bytes2Hex(sha256.digest());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return text;
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 *
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 *
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	static {
		try {
			init(strDefaultKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 默认构造方法，使用默认密钥
	 *
	 * @throws Exception
	 */
	public EncryptUtils() throws Exception {
		this(strDefaultKey);
	}

	/**
	 * 指定密钥构造方法
	 *
	 * @param strKey
	 *            指定的密钥
	 * @throws Exception
	 */
	public EncryptUtils(String strKey) throws Exception {
		init(strKey);
	}

	public static void init(String strKey)throws Exception{
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}
	/**
	 * 加密字节数组
	 *
	 * @param arrB
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	/**
	 * 加密字符串
	 *
	 * @param strIn
	 *            需加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	/**
	 * 解密字节数组
	 *
	 * @param arrB
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * 解密字符串
	 *
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decrypt(String strIn) throws Exception {
		if(strIn.startsWith("encrypt_")){
			return new String(decrypt(hexStr2ByteArr(strIn.split("_")[1])));
		}
		else {

			return  strIn;
		}
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 *
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws Exception
	 */
	private static  Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	/**
	 * main方法。
	 * @author 姜文杰
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String test = "abcd1234";
			EncryptUtils des = new EncryptUtils();//自定义密钥
//			System.out.println("加密后的字符：encrypt_" + des.encrypt(test));
//			System.out.println("解密后的字符：" + des.decrypt("encrypt_"+des.encrypt(test)));
//			System.out.println("解密后的字符：" + EncryptUtils.decrypt("encrypt_d725394d5ec68f166e8662b6d7f37c27"));

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
