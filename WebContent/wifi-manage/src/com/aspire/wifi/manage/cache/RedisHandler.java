package com.aspire.wifi.manage.cache;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.aspire.wifi.manage.util.ConfigPathBean;

/**
 * 基于jedis操作redis数据库的处理类
 * 
 * 
 * @author zhangjie
 */
public class RedisHandler {

	private static RedisHandler redis = new RedisHandler();
	private static Logger logger =  LoggerFactory.getLogger(RedisHandler.class);

	/**
	 * redis pool池配置
	 */
	private JedisPoolConfig config = new JedisPoolConfig();
	/**
	 * redis pool池
	 */
	private JedisPool pool;

	private Properties properties = null;

	private String host = "127.0.0.1";
	private int port = 6379;
	private boolean isAuth = false;
	private String password = "";

	private RedisHandler() {

		initConfig();

	}

	public static RedisHandler getInstance() {

		return redis;
	}
	/**
	 * 读取配置文件redis.properties
	 */
	private void initConfig() 
	{
		InputStream fileInputStream = null;
		try {
			/* 加载authdb.properties配置文件 */
			String fileName = ConfigPathBean.getRedisConfigPath();
//			String fileName ="/home/chenping/code/手机快捷支付/02研发管理/03代码/MQP/server/webapp/WEB-INF/config/system/redis.properties";
//			logger.info("redis配置文件 path=" + fileName);
			properties = new Properties();
			fileInputStream = new FileInputStream(fileName);
			properties.load(fileInputStream);
		} catch (Exception e) {
			logger.error("Redis配置文件初始化失败!", e);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e1) {
				}
			}
		}

		init();
	}

	/**
	 * 初始化参数
	 */
	private void init() {
		host = properties.getProperty("host");
		port = Integer.parseInt(properties.getProperty("port"));
		isAuth = Boolean.parseBoolean(properties.getProperty("isAuth"));
		password = properties.getProperty("password");
		config.setMaxActive(500);
		config.setMaxIdle(200);
		config.setMaxWait(2000l);
		pool = new JedisPool(config, host, port);
	}

	/**
	 * 从池里获取一个redis客服端
	 */
	public Jedis getJedis() {
		Jedis jedis = pool.getResource();
		if (!jedis.isConnected()) {
			logger.debug("redis reconnect.");
			jedis.connect();
		}
		if (isAuth) {
			jedis.auth(password);
		}
		return jedis;
	}

	/**
	 * 设置对象
	 */
	public Object setObject(String key, Object obj) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.set(key, SerializableHandler.objectToString(obj));

		} catch (Exception e) {
			logger.error("setObject failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
		return obj;
	}
	 
	/**
	 * 设置对象（带过期时间，单位：秒）
	 */
	public void setObject(String key, Object obj, int time) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.set(key, SerializableHandler.objectToString(obj));
			jedis.expire(key, time);// 过期时间
		} catch (Exception e) {
			logger.error("setObject failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
	}

	/**
	 * 设置对象（带过期时间，单位：秒）
	 */
	public void setString(String key, String obj, int time) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.set(key, obj);
			jedis.expire(key, time);// 过期时间
		} catch (Exception e) {
			logger.error("setObject failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
	}
	
	public void setString(String key, String obj) {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.set(key, obj);
		} catch (Exception e) {
			logger.error("setObject failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
	}
	
	/**
	 * 获取对象
	 */
	public String getString(String key) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = this.getJedis();
			value = jedis.get(key);
		} catch (Exception e) {
			logger.error("getObject failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * 设置链表对象
	 * @param key
	 * @param str
	 * @param maxElement
	 * @param sec
	 * @return
	 */
	public synchronized boolean synPushObject(String key, String str,Integer maxElement,int sec) throws Exception{
		Jedis jedis = null;
		boolean flag = true;
		try {
			jedis = this.getJedis();
			if(jedis.llen(key)>maxElement){
				flag = false;
				logger.debug("redis中"+key+"集合的元素个数以达到设定最大值");
			}else{
				//新增后返回的长度
				long len = jedis.lpush(key, str);
				if(len==1){
					jedis.expire(key, sec);// 过期时间
				}else if(len>maxElement){
					flag = false;
					jedis.lpop(key);
				}
			}
		} catch (Exception e) {
			flag = false;
			logger.error("pushObject failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
			throw e;
		} finally {
			pool.returnResource(jedis);
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @param key
	 * @param str
	 * @param sec
	 * @return
	 */
	public boolean pushObject(String key, String str,int sec) {
		Jedis jedis = null;
		boolean flag = true;
		try {
			jedis = this.getJedis();
			long len = jedis.lpush(key, str);
			if(len==1){
				jedis.expire(key, sec);// 过期时间
			}
		} catch (Exception e) {
			flag = false;
			logger.error("pushObject failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
		return flag;
	}
	
	/**
	 * 
	 * @param key
	 * @param str
	 * @param sec
	 * @return
	 */
	public boolean pushNewObject(String key, String str,int sec,int maxElement) {
		Jedis jedis = null;
		boolean flag = true;
		try {
			jedis = this.getJedis();
			long len = jedis.lpush(key, str);
			if(len==1){
				jedis.expire(key, sec);// 过期时间
			}else if(len>maxElement){
				flag = false;
				jedis.rpop(key);
			}
		} catch (Exception e) {
			flag = false;
			logger.error("pushObject failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
		return flag;
	}
	
	/**
	 * 返回集合
	 * @param key
	 * @param start
	 * @param end
	 */
	public List<String> getList(String key,long start,long end) {
		Jedis jedis = null;
		List<String> list = null;
		try {
			jedis = this.getJedis();
			list = jedis.lrange(key, start, end); 
		} catch (Exception e) {
			logger.error("getAllObject failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
		return list;
	}
	
	
	/**
	 * 获取链表的元素个数
	 * @param key
	 */
	public long listSize(String key){
		Jedis jedis = null;
		long length=0;
		try {
			jedis = this.getJedis();
			length = jedis.llen(key);
		} catch (Exception e) {
			logger.error("size failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
		return length;
	}
	
	
	/**
	 * 获取对象
	 */
	public Object getObject(String key) {
		Jedis jedis = null;
		Object obj = null;
		try {
			jedis = this.getJedis();
			String value = jedis.get(key);
			obj = SerializableHandler.stringToObject(value);
		} catch (Exception e) {
			logger.error("getObject failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
		return obj;
	}

	/**
	 * 删除对象
	 */
	public Object delObject(String key) {
		Jedis jedis = null;
		Object obj = null;
		try {
			jedis = this.getJedis();
			obj = jedis.del(key);
		} catch (Exception e) {
			logger.error("delObject failed. key=" + key, e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
		return obj;
	}

	/**
	 * 删除所以对象
	 */
	public void clear() {
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.flushAll();
		} catch (Exception e) {
			logger.error("clear failed.", e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
	}

	/**
	 * 获取对象总数
	 */
	public int size() {
		Jedis jedis = null;
		int size = 0;
		try {
			jedis = this.getJedis();
			size = Long.bitCount(jedis.dbSize());
		} catch (Exception e) {
			logger.error("clear failed.", e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
		return size;
	}
	
	/**
	 * 模糊匹配获取key集合
	 * @param key
	 * @return
	 */
	public Set<String> getKeySet(String key){
		Jedis jedis = null;
		Set<String> keySet = null;
		try {
			jedis = this.getJedis();
			keySet = jedis.keys(key);
		} catch (Exception e) {
			logger.error("clear failed.", e);
			pool.returnBrokenResource(jedis);
		} finally {
			pool.returnResource(jedis);
		}
		return keySet;
	}
	

	// test实例
//	public static void main(String[] args) {
//		System.out.println(String.valueOf(new java.util.Date(System
//				.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000)
//				.toLocaleString()));
		// JRedis jr = new JRedisClient("192.168.56.55",6379);
//		 RedisHandler redis = RedisHandler.getInstance();
		// try {
		// String key = "key2";
//		 redis.setObject("DT_13048930877", "abcde234");
//		 System.out.println(redis.getObject("DT_13048930877"));
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
//	}

}