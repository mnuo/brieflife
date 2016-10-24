/**
 * PositionDao.java created at 2016年10月24日 下午9:51:03
 */
package com.mnuo.brieflife.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mnuo.brieflife.dao.AbstractHibernateDao;
import com.mnuo.brieflife.entity.BlPosition;

/**
 * @author saxon
 */
@Repository
@Transactional
public class PositionDaoImpl extends AbstractHibernateDao<BlPosition> implements com.mnuo.brieflife.dao.PositionDao {

	@SuppressWarnings("unchecked")
	@Override
	public BlPosition queryByUserId(String userId) {
		Criteria c= getCurrentSession().createCriteria(BlPosition.class);
		c.add(Restrictions.eq("userId",userId));//eq是等于，gt是大于，lt是小于,or是或
		List<BlPosition> list = c.list();
		return (list!= null && list.size()>1) ? list.get(0):null;
	}

	
}
