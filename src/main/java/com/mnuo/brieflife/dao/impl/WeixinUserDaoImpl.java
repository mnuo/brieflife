/**
 * WeixinUserDaoImpl.java created at 2016年10月23日 下午2:58:32
 */
package com.mnuo.brieflife.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mnuo.brieflife.dao.AbstractHibernateDao;
import com.mnuo.brieflife.dao.WeixinUserDao;
import com.mnuo.brieflife.entity.BlUser;

/**
 * @author saxon
 */
@Repository
@Transactional
public class WeixinUserDaoImpl extends AbstractHibernateDao<BlUser> implements WeixinUserDao {

}
