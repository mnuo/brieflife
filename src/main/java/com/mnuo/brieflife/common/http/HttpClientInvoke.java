/**
 * HttpClientInvoke.java created at 2016年10月23日 下午1:38:12
 */
package com.mnuo.brieflife.common.http;

import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author saxon
 */
public class HttpClientInvoke {
	public String invokeAdapter(String serverUrl, String url, String templateName) throws Exception {
        NameValuePair[] param = { new NameValuePair("url", url),
        		new NameValuePair("templateName", templateName),
        		new NameValuePair("table", templateName)};
       
        return httpHandle(serverUrl, param);
	}
	
	/**
	 * http请求
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String httpHandle(String url, NameValuePair[] params) throws Exception{
		HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.setRequestBody(params);
        method.getParams().setContentCharset("utf-8");
        int code = client.executeMethod(method);
        if (code != 200) {
        	throw new Exception("调用" + url + "请求失败，接口返回ERROR");
        }
        return method.getResponseBodyAsString();
	}
	/**
	 * http请求
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static InputStream httpStreamPost(String url, NameValuePair[] params) throws Exception{
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.setRequestBody(params);
		method.getParams().setContentCharset("utf-8");
		int code = client.executeMethod(method);
		if (code != 200) {
			throw new Exception("调用" + url + "请求失败，接口返回ERROR");
		}
		return method.getResponseBodyAsStream();
	}
	/**
	 * http请求 GET
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static InputStream httpStreamGet(String url) throws Exception{
		HttpClient client = new HttpClient();
        // 实例化HTTP方法  
		GetMethod method = new GetMethod(url);
        int code = client.executeMethod(method); 
		if (code != 200) {
			throw new Exception("调用" + url + "请求失败，接口返回ERROR");
		}
		return method.getResponseBodyAsStream();
	}
	/**
	 * http请求 GET
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String httpStreamGetString(String url) throws Exception{
		HttpClient client = new HttpClient();
		// 实例化HTTP方法  
		GetMethod method = new GetMethod(url);
		int code = client.executeMethod(method); 
		if (code != 200) {
			throw new Exception("调用" + url + "请求失败，接口返回ERROR");
		}
		return method.getResponseBodyAsString();
	}
}
