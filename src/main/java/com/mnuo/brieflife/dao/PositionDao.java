/**
 * PositionDao.java created at 2016年10月24日 下午9:47:57
 */
package com.mnuo.brieflife.dao;

import com.mnuo.brieflife.entity.BlPosition;

/**
 * @author saxon
 */
public interface PositionDao extends IHibernateDao<BlPosition> {
	BlPosition queryByUserId(String userId);
}
