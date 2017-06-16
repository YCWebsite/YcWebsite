package com.yc.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.yc.redis.RedisCache;

public class GetIp {
	/**
	 * 日志对象
	 */
	private static Logger logger=LogManager.getLogger(   GetIp.class)   ;
	//获取ip地址
	public String getRemortIP(HttpServletRequest request) {  
	     if (request.getHeader("x-forwarded-for") == null) { 
	    	 logger.debug("the person who "+request.getRemoteAddr()+" was logining the system.");
	        return request.getRemoteAddr();  
	    }  
	    logger.debug("the person who "+request.getHeader("x-forwarded-for")+"was logining the system.");
	    return request.getHeader("x-forwarded-for");  
	}
		
}
