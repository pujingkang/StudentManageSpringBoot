package com.souls.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

//@Component
//@ConfigurationProperties(prefix = "redis")
public class RedisUtil 
{
	// @Value("${redis.host}")
	private String host;//="192.168.1.58";

	private Integer port;//=6379;
	private String password;//="root";
	
	private static Jedis jredis=null;

	private  void getJedis()
	{
		if(jredis==null)
		{
			RedisUtil.jredis=new Jedis(host,port);
			if(!(password==null||"".equals(password)))
				jredis.auth(password);
		}

	}

	
	public RedisUtil()
	{

	}
	
	public boolean setString(String key,String value)
	{
		getJedis();
		if(key==null||value==null) return false;
		return "OK".equals(RedisUtil.jredis.set(key, value));
	}
	
	public boolean setObjectByHash(String key, Object value)
	{
		getJedis();
		if(key==null||value==null) return false;
		Class clz=value.getClass();
		Field[] fields=clz.getDeclaredFields();
		try{
			for (Field field : fields)
			{
				field.setAccessible(true);
				Object v=field.get(value);
				if(v==null) continue;
				RedisUtil.jredis.hset(key, field.getName(), v.toString());
			}
			
		}catch(Exception e)
		{
			
		}
		return true;
	}
	

	public String getString(String key)
	{
		getJedis();
		return RedisUtil.jredis.get(key);
	}


	
	public boolean removeKey(String key)
	{
		getJedis();
		if(key==null||"".equals(key)) return false;
		return RedisUtil.jredis.del(key)>0;
	}
	
	public boolean saveObjectByByte(String key, Object obj)
	{
		getJedis();
		if(key==null||obj==null) return false;
		return "OK".equals(RedisUtil.jredis.set(key.getBytes(), toByte(obj)));
	}
	public Object getObjectFromByte(String key)
	{
		getJedis();
		if(key==null) return false;
		return toObject(RedisUtil.jredis.get(key.getBytes()));
	}
	
	
	public  byte[] toByte(Object obj) {
		getJedis();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	ObjectOutputStream oos = null;
		byte[] bytes = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bytes = baos.toByteArray();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if(baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return bytes;
	}
	
	public  Object toObject(byte[] bytes) {
		getJedis();
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(bais != null) {
				try {
					bais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static Jedis getJredis() {
		return jredis;
	}

	public static void setJredis(Jedis jredis) {
		RedisUtil.jredis = jredis;
	}

	
}
