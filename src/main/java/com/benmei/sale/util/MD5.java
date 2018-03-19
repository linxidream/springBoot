package com.benmei.sale.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 * User: LichFaker
 * Date: 2014-11-10
 * Time: 上午11:30:53
 * Email: lichfaker@gmail.com
 */
public class MD5 {

	/**
	 * 对传入的str字符串进行md5算法加密，并返回加密后的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String code(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] data = digest.digest(str.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < data.length; i++) {
				String result = Integer.toHexString(data[i] & 0xff);
				if (result.length() == 1) {
					result = "0" + result;
				}
				sb.append(result);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}
}
