/**
 * BaseMessageServiceImpl.java created at 2016年10月23日 上午11:13:37
 */
package com.mnuo.brieflife.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnuo.brieflife.dao.BaseMessageDao;
import com.mnuo.brieflife.entity.BlTextMessage;
import com.mnuo.brieflife.service.BaseMessageService;

/**
 * @author saxon
 */
@Service
@Transactional
public class BaseMessageServiceImpl implements BaseMessageService {
	@Autowired
	public BaseMessageDao baseMessageDao;
	
	@Override
	public void save(BlTextMessage baseMessage) {
		baseMessageDao.create((BlTextMessage) baseMessage);
	}

	public BaseMessageDao getBaseMessageDao() {
		return baseMessageDao;
	}

	public void setBaseMessageDao(BaseMessageDao baseMessageDao) {
		this.baseMessageDao = baseMessageDao;
	}
}
