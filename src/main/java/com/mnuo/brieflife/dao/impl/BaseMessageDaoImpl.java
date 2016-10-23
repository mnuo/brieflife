/**
 * TextMessageDaoImpl.java created at 2016年10月23日 上午11:33:14
 */
package com.mnuo.brieflife.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mnuo.brieflife.dao.AbstractHibernateDao;
import com.mnuo.brieflife.dao.BaseMessageDao;
import com.mnuo.brieflife.entity.BaseMessage;

/**
 * @author saxon
 */
@Repository
@Transactional
public class BaseMessageDaoImpl extends AbstractHibernateDao<BaseMessage> implements BaseMessageDao {

}
