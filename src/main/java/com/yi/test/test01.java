package com.yi.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class test01 {
	private static String host = "127.0.0.1";
	//private static String host = "192.168.1.198";
	private static int port = 6379;
	private static String password = "rootOz@2016d";

	/*
	 * @Test public void test1() { Jedis jedis = new Jedis(host, port);
	 * jedis.auth(auth); try {
	 * 
	 * if (jedis.get("k1") == null) { jedis.set("k1", "value1"); } if
	 * (jedis.get("k2") == null) { jedis.set("k2", "0"); } if (jedis.get("k3")
	 * == null) { jedis.set("k3", "0"); } jedis.incr("k2"); // 递增
	 * jedis.incrBy("k3", 2); // 递增
	 * 
	 * System.out.println("-----1-----:" + jedis.get("k1"));
	 * System.out.println("-----2-----:" + jedis.get("k2"));
	 * System.out.println("-----3-----:" + jedis.get("k3"));
	 * 
	 * } catch (Exception e) { System.out.println("Exception---:" +
	 * e.getMessage()); } finally { if (jedis != null) { jedis.disconnect(); } }
	 * }
	 */

	@Test
	public void test3() {
		JedisPoolConfig jpl = new JedisPoolConfig();
		// 设置一个JedisPool最多可分配多少个jedis实例，可通过JedisPool.getResource()来获取,
		// 设置为-1表示不限制，如果JedisPool已经分配了MaxActive，则此时该jedisPool的状态为exhausted(耗尽)
		jpl.setMaxTotal(50000);
		// 设置最大空闲实例数
		jpl.setMaxIdle(5);
		// 表示引用一个jedis实例时，最大等待时间，如果超出，则抛出JedisConnectionException;
		jpl.setMaxWaitMillis(1000*60*5);
		// 表示在引用一个jedis实例时，是否进行验证操作，为true表示得到的实例都是可用的
		jpl.setTestOnBorrow(true);
		JedisPool jp = new JedisPool(jpl, host, port);
		//JedisPool jp = new JedisPool(jpl, host, port, 1000*60*5, password);
		Jedis j = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

			long start = System.currentTimeMillis();
			System.out.println("使用连接池递增开始时间: " + df.format(new Date()));
			
			for(int i =0;i<5000;i++){  
		         j = jp.getResource();  
		         j.incr("k3");  
		         //System.out.println("---3----: " + j.get("k3"));
		     } 			
			
			long end = System.currentTimeMillis();
			System.out.println("使用连接池递增结束时间: " + df.format(new Date()));
			
			long h = end-start;
			System.out.println("耗时: " + h );

		} catch (Exception e) {
			System.out.println("Exception---: " + e.getMessage());
		} finally {
			if (j != null)
				jp.returnResource(j); // 放回连接连接池
		}
	}
}
