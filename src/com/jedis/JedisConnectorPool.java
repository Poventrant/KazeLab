package com.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisConnectorPool {
    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
        /// Jedis implements Closable. Hence, the jedis instance will be auto-closed after the last statement.
        Jedis jedis = pool.getResource();
        try {
            jedis.set("foo", "bar");
            String value = jedis.get("foo");
            System.out.println(value);

            List<String> lists = jedis.lrange("mylist", 0, -1);
            for(String list: lists) {
                System.out.println(list);
            }

            Map<String, String> map = jedis.hgetAll("user:1000");
            for(Map.Entry<String, String> e : map.entrySet()) {
                System.out.println(e.getKey() + ":" + e.getValue());
            }
        } finally {
            jedis.close();
        }
        /// ... when closing your application:
        pool.destroy();
    }
}
