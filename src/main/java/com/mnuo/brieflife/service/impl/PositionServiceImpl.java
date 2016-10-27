/**
 * PositionServiceImpl.java created at 2016年10月24日 下午6:06:29
 */
package com.mnuo.brieflife.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnuo.brieflife.dao.PositionDao;
import com.mnuo.brieflife.entity.BlPosition;
import com.mnuo.brieflife.service.PositionService;

/**
 * @author saxon
 */
@Service
@Transactional
public class PositionServiceImpl implements PositionService {
	private static Logger logger = Logger.getLogger(PositionServiceImpl.class);
	@Autowired
	PositionDao positionDao;
	
	@Cacheable(value="positionCache")// 使用了一个缓存名叫 accountCache   
	@Override
	public BlPosition getPosition(String userId) {
		logger.debug("\n执行方法,不从缓存中取数据....");
		BlPosition entity = positionDao.queryByUserId(userId);
		if(entity == null){
			entity = new BlPosition();
			entity.setStatus(0);
			entity.setCreatedTime(new Date());
			entity.setUserId(userId);
			positionDao.create(entity);
		}
		return entity;
	}

	@Override
	public void saveOrUpdate(BlPosition position) {
		if(position.getId() != null && position.getId() > 0){
			positionDao.create(position);
		}else{
			positionDao.update(position);
		}
	}

	public PositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}
}
