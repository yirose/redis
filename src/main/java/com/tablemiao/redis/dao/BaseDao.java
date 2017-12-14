package com.tablemiao.redis.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

public class BaseDao {

	protected RedisTemplate<String, Object> redisTemplate;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("redisTemplate")
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}