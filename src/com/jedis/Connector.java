package com.jedis;

import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Connector {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost");
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

        Long start = System.currentTimeMillis();
        Pipeline p = jedis.pipelined();
        for (int i = 0; i < 10; i++) {
            p.set(String.valueOf(i),String.valueOf(i));
        }
        p.sync();
        Long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            jedis.set(String.valueOf(i),String.valueOf(i));
        }
        end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
        jedis.close();

        jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        jedis.flushAll();


        jedis = new Jedis("127.0.0.1", 6379);
        p = jedis.pipelined();
        p.multi();
        for (int i = 0; i < 10000; i++) {
            p.incr( "foo");
        }
        p.exec();
        p.sync();


        List<JedisShardInfo> shards = Arrays.asList(
                new JedisShardInfo("localhost",6379),
                new JedisShardInfo("localhost",6380));

        ShardedJedis sharding = new ShardedJedis(shards);

        for (int i = 0; i < 100000; i++) {
            sharding.incr("sn");
        }

        sharding.disconnect();

        sharding = new ShardedJedis(shards);

        ShardedJedisPipeline pipeline = sharding.pipelined();
        for (int i = 0; i < 100000; i++) {
            pipeline.incr("sp");
        }
        pipeline.sync();

        sharding.disconnect();


        ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);

        ShardedJedis one = pool.getResource();

        pipeline = one.pipelined();

        for (int i = 0; i < 100000; i++) {
            pipeline.set("sppn" + i, "n" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        one.close();
        pool.close();

        System.out.println("Pipelined@Pool SET: " + ((end - start)/1000.0) + " seconds");
        pool.destroy();

    }
}
