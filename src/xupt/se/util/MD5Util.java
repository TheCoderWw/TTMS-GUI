package xupt.se.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 */
public class MD5Util {

	public static String crypt(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("String to encript cannot be null or zero length");
		}
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); // MessageDigest --> 信息摘要
			md.update((str).getBytes()); // 使用指定的字节数组更新摘要。
			byte[] hash = md.digest(); // 使用指定的字节数组对摘要执行最终更新，然后完成摘要计算
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

}
