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
	
	public static String toLowerCase(String in){
		char[] arr = in.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(char c : arr){
			String str = c + "";
			if(Character.isUpperCase(c)){
				str = "_" + c;
			}
			sb.append(str);
		}
		return sb.toString().toLowerCase();
	}
	
}
