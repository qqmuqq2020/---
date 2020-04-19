package cn.itrip.common;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
public class RedisHelp {
    //get 一个set redis的资源
    //这种方法用的是new一个类的方法，还有一种是使用连接池的方法；
    public  void setRedis(String key,String value,int expire)
    {
        Jedis redis=new Jedis("192.168.124.28",6379);
        redis.auth("123456");

        redis.setex(key,expire,value);
    }
    //set  jedis 实例的方法
    public String getkey(String key)
    {
        Jedis redis=new Jedis("192.168.124.28",6379);
        redis.auth("123456");
        return redis.get(key);
    }
    public String set(String key,int seconds, String value) {
        Jedis redis=new Jedis("192.168.124.28",6379);
        String value2 = redis.setex(key, seconds, value);
        return value2;
    }
    //exists
    public static boolean exist(String key)
    {
        Jedis redis=new Jedis("192.168.124.28",6379);
        redis.auth("123456");
        return redis.exists(key);
    }
    //ttl
    //keys
}
