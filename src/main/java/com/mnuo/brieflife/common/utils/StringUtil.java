/**
 * StringUtil.java created at 2016年10月22日 下午5:35:52
 */
package com.mnuo.brieflife.common.utils;

/**
 * @author saxon
 */
public class StringUtil {
	public static boolean isNotEmpty(String in){
		if(in == null || "".equals(in))
			return false;
		
		return true;
	}
}
