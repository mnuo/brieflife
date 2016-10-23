/**
 * WeixinUserServiceImpl.java created at 2016年10月23日 下午2:56:25
 */
package com.mnuo.brieflife.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnuo.brieflife.dao.WeixinUserDao;
import com.mnuo.brieflife.entity.BlUser;
import com.mnuo.brieflife.service.WeixinUserService;

/**
 * @author saxon
 */
@Service
@Transactional
public class WeixinUserServiceImpl implements WeixinUserService {
	
	@Autowired
	private WeixinUserDao weixinUserDao; 
	
	@Override
	public void save(BlUser user) {
		weixinUserDao.create(user);
	}

	public WeixinUserDao getWeixinUserDao() {
		return weixinUserDao;
	}

	public void setWeixinUserDao(WeixinUserDao weixinUserDao) {
		this.weixinUserDao = weixinUserDao;
	}
}
