package com.tablemiao.redis.dao;

import java.util.List;

import org.springframework.data.redis.connection.DataType;
import org.springframework.stereotype.Repository;

@Repository
public class CustomDao extends BaseDao {

	public boolean insertString(String key, String value) {
		try {
			super.redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			logger.info("新增错误:{}", e.getMessage());
			return false;
		}

	}

	public boolean insertList(String key, Object value) {
		try {
			super.redisTemplate.opsForList().leftPushAll(key, value);
			return true;
		} catch (Exception e) {
			logger.info("新增错误:{}", e.getMessage());
			return false;
		}

	}

	public boolean insertHash(String key, String sonKey, Object value) {
		try {
			super.redisTemplate.opsForHash().put(key, sonKey, value);
			return true;
		} catch (Exception e) {
			logger.info("新增错误:{}", e.getMessage());
			return false;
		}

	}

	public Object select(String key) {
		try {
			DataType type = redisTemplate.type(key);
			if (DataType.NONE == type) {
				logger.info("key不存在");
				return null;
			} else if (DataType.STRING == type) {
				return super.redisTemplate.opsForValue().get(key);
			} else if (DataType.LIST == type) {
				return super.redisTemplate.opsForList().range(key, 0, -1);
			} else if (DataType.HASH == type) {
				return super.redisTemplate.opsForHash().entries(key);
			} else
				return null;
		} catch (Exception e) {
			logger.info("查询错误:{}", e.getMessage());
			return null;
		}
	}

	public boolean delete(List<String> keys) {
		try {
			redisTemplate.delete(keys);
			return true;
		} catch (Exception e) {
			logger.info("删除失败:{}", e.getMessage());
			return false;
		}
	}
}