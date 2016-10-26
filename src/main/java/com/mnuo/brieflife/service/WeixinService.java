/**
 * WeixinService.java created at 2016年10月26日 上午11:30:01
 */
package com.mnuo.brieflife.service;

import java.util.Map;

import com.mnuo.brieflife.dto.BaseMessageXML;

/**
 * @author saxon
 */
public interface WeixinService {

	BaseMessageXML unifyHandler(Map<String, String> map);

}
