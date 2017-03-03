package com.yc.redis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {
	/*
	 * 日志对象
	 */
	private static Logger logger=LogManager.getLogger(   RedisCache.class)   ; 
	/**
	 * 对象的编号
	 */
	private String id;
	
	private Jedis redisClient=createRedis();
	
	//用于同步的锁
	private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	
	public RedisCache( String id){
		if( id==null ){
			throw new IllegalArgumentException("Cache instance requires an Id");
		}
		logger.debug("create an cache instance with id:"+ id);
		this.id=id;
	}
	
	/**
	 * jedis从联接池中取
	 * @return
	 */
	public static Jedis createRedis(){
		//TODO: 获取jedis实例  -> 这个地址要变
		//Jedis jedis = new Jedis("192.168.137.128");
		Jedis jedis=   RedisPool.getPool().getResource();
		return jedis;
	}
	
	/**
	 * 获取当前的缓存号
	 */
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		byte[] keybyte= SerializableUtil.serialize(key);
		byte[] valuebyte=SerializableUtil.serialize(value);
		this.redisClient.set(keybyte, valuebyte);
	}

	@Override //通过key到缓存redis中取值
	public Object getObject(Object key) {
		//缓存穿透.
		byte[] values=this.redisClient.get(  SerializableUtil.serialize(key)       );
		//System.out.println(   values );
		if(  values==null  ){
			//this.putObject(  SerializableUtil.serialize(key)  , null);
			return null;
		}
		Object obj=SerializableUtil.unSerialize(  values);
		logger.info("redis get an Object with key"+key+":value is:"+obj);
		return obj;
	}

	@Override
	public Object removeObject(Object key) {
		byte[] keybyte= SerializableUtil.serialize(key);
		return this.redisClient.expire(keybyte, 0);
	}

	@Override  //将缓存中的数据删除，实际上就是要清空redis的数据
	public void clear() {
		this.redisClient.flushDB();    
	}

	@Override
	public int getSize() {
		Long size=this.redisClient.dbSize();
		int s=Integer.valueOf(  size+"");
		return s;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

}
