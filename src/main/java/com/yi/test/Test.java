package com.yi.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.tablemiao.redis.dao.CustomDao;



@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test extends AbstractJUnit4SpringContextTests {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomDao c;

	@org.junit.Test
	public void test() {

		logger.info("=========start===========");
		
		 // String的操作 
		  c.insertString("java:string:english", "java_insert_string");
		  logger.info("查询redis中的值:{}",c.select("java:string:english"));
		  c.insertString("java:string:cn", "redis学习");
		  logger.info("查询redis中的值:{}",c.select("java:string:cn"));
		 

		/*
		 * 列表操作 List<Object> list = new ArrayList<Object>();
		 * list.add("redis学习"); list.add("123456789"); c.insertList("java:list",
		 * list); logger.info("查询redis中的值:{}",c.select("java:list")); //创建一个列表之后
		 * 再次添加一个Map到列表里面 Map<String,String> map = new HashMap<String,String>();
		 * map.put("A", "sss"); map.put("AB", "sssdf");
		 * c.insertList("java:list", map.toString());
		 * logger.info("查询redis中的值:{}",c.select("java:list"));
		 */

		/*
		 * 哈希表操作 c.insertHash("java:map", "key1", "123"); //得到这个Hash类型 再次放入一个对象
		 * CustomEntity bean = new CustomEntity(); bean.setA(123); bean.setB(new
		 * Date()); bean.setC("ABC");
		 * logger.info("查询redis中的值:{}",c.select("java:map"));
		 * c.insertHash("java:map", "key2", bean.toString());
		 * logger.info("查询redis中的值:{}",c.select("java:map"));
		 */

		/*
		 * 读取redis中的数据 logger.info("查询redis中的值:{}",c.select("java:map")); Object
		 * map = c.select("java:map"); if(map instanceof Map){
		 * Map<String,Object> resultMap = (Map<String,Object>)map;
		 * 
		 * Iterator<Object> it = resultMap.values().iterator();
		 * while(it.hasNext()) logger.info("遍历Map:{}",it.next()); }
		 */

		/*
		 * 删除数据 logger.info("查询redis中的值:{}",c.select("java:map")); List<String>
		 * keys = new ArrayList<String>(); keys.add("java:map"); c.delete(keys);
		 * logger.info("查询redis中的值:{}",c.select("java:map"));
		 */
		logger.info("=========end===========");
	}
}