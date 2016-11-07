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
	
	public static String toFirstLowerCase(String in){
		char[] arr = in.toCharArray();
		StringBuffer sb = new StringBuffer();
		
		char first = arr[0];
		
		sb.append((first + "").toLowerCase());
		sb.append(in.substring(1));
		return sb.toString();
	}
}
