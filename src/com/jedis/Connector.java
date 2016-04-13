package com.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

/*
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 6379));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        jc.set("foo", "bar");
        String value = jc.get("foo");
        System.out.println(value);
*/

    }
}
