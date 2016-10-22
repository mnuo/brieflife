/**
 * WeixinCheck.java created at 2016年10月22日 下午5:05:16
 */
package com.mnuo.brieflife.common;

import java.util.Arrays;

import com.mnuo.brieflife.common.utils.NusumSignature;

/**
 * @author saxon
 */
public class WeixinCheck {
	public static boolean checkFromWeixinOne(String[] str, String signature){
		try {
			Arrays.sort(str); // 字典序排序
			String bigStr = str[0] + str[1] + str[2];
			String digest = NusumSignature.getSHA1Code(bigStr);//SHA1加密
			return digest.equals(signature);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
