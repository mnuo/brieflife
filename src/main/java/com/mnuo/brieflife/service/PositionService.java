/**
 * PositionService.java created at 2016年10月24日 下午6:06:10
 */
package com.mnuo.brieflife.service;

import com.mnuo.brieflife.entity.BlPosition;

/**
 * @author saxon
 */
public interface PositionService {
	BlPosition getPosition(String userId);

	void saveOrUpdate(BlPosition position);
}
