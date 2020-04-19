package cn.itrip.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisAPI {

    public JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
     //get
    public String get(String key){
        Jedis jedis = jedisPool.getResource();
        String value =jedis.get(key);
        jedisPool.returnResource(jedis);
        return value;
    }
    //set
    public  String  set(String key,String value) {
        Jedis jedis = jedisPool.getResource();
        String value2 = jedis.set(key, value);
        jedisPool.returnResource(jedis);
        return value2;
    }
    public String set(String key,int seconds, String value) {
        Jedis jedis = jedisPool.getResource();
        String value2 = jedis.setex(key, seconds, value);
        jedisPool.returnResource(jedis);
        return value2;
    }
    //exists
        public boolean exists(String key)
        {
            Jedis jedis1 = jedisPool.getResource();
           Boolean result=jedis1.exists(key);
            jedisPool.returnResource(jedis1);
            return result;
        }
    //ttl
    public long ttl(String key)
    {
        Jedis jedis1 = jedisPool.getResource();
        Long result=jedis1.ttl(key);
        jedisPool.returnResource(jedis1);
        return result;
    }

    //del
    public Long del(String keys)
    {
        Jedis jedis1 = jedisPool.getResource();
        Long result=jedis1.del(keys);
        jedisPool.returnResource(jedis1);
        return result;
    }

}
