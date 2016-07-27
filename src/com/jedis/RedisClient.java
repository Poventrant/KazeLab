package com.jedis;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * redis 链接客户端
 * 2016-06-10 庞文全
 */
public class RedisClient {

    final static Logger logger = Logger.getLogger(RedisClient.class);

    private static String HOST_NAME = "";

    private static JedisPool jedisPool;

    static {
        PropertiesLoader redisProper = new PropertiesLoader("redis.properties");
        String redisPass = redisProper.getProperty("remote.password");

        int redisTimeout = 15000;
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(16);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(300000);
        jedisPoolConfig.setNumTestsPerEvictionRun(3);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(60000);

        //尝试不用密码链接
        jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379, redisTimeout);
        try {
            //测试连接
            Jedis jedis = jedisPool.getResource();
            jedis.keys("_");
        } catch (Exception e) {
            //尝试使用密码连接
            jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379, redisTimeout, redisPass);
            try {
                Jedis jedis = jedisPool.getResource();
                jedis.keys("_");
            } catch (Exception e1) {
                logger.warn("连接本地redis失败，尝试连接远程redis");
                jedisPool.close();

                String dev = redisProper.getProperty("remote.dev");
                if ("false".equals(dev)) {
                    try {
                        InetAddress addr;
                        addr = InetAddress.getLocalHost();
                        HOST_NAME = addr.getHostName().replaceAll("-", "_") + "_";
                    } catch (UnknownHostException ex) {
                        System.out.println("Hostname can not be resolved");
                    }
                }

                String redisHost = redisProper.getProperty("remote.host");
                Integer redisDport = redisProper.getInteger("remote.port");
                if (redisPass != null && "".equals(redisPass)) redisPass = null;
                jedisPool = new JedisPool(jedisPoolConfig, redisHost, redisDport, redisTimeout, redisPass);
                try {
                    Jedis jedis = jedisPool.getResource();
                    jedis.keys("_");
                } catch (Exception e2) {
                    logger.error("-----------------连接远程redis失败------------------", e2);
                }
            }
        }
    }

    public static Jedis getRedisClient() {
        try {
            return jedisPool.getResource();
        } catch (Exception e) {
            logger.error("error!", e);
            throw new RuntimeException("请求队伍信息超时");
        }
    }

    /**
     * 自旋获取jedis实体
     * @return
     */
    public static Jedis doGetRedisClient() {
        Jedis jedis = null;
        while(jedis == null) {
            try {
                jedis = jedisPool.getResource();
            } catch (Exception e) {}
        }
        return jedis;
    }

    /**
     * 归还redis资源到连接池
     * @param jedisInstance
     */
    public static void closeRedis(Jedis jedisInstance) {
        if(jedisInstance == null) return;
        jedisInstance.close();
    }

    public static void shutdown() {
        jedisPool.close();
    }

    public static String getHostName() {
        return HOST_NAME;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            new Thread() {
                @Override
                public void run() {
                    while(true) {
                        final Jedis jedis = RedisClient.getRedisClient();
                        try {
                            jedis.subscribe(new OvertimePubSub(), "test");
                        } finally {
                            jedis.close();
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        new Thread() {
            @Override
            public void run() {
                while(true) {
                    final Jedis jedis = RedisClient.getRedisClient();
                    try {
                        jedis.publish("test", "hello");
                    } finally {
                        jedis.close();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int poolsize = 100;
        ExecutorService executor = Executors.newFixedThreadPool(poolsize);
        for (int i = 0; i < poolsize; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Jedis jedis = RedisClient.getRedisClient();
                        try {
                            Long len = jedis.llen("seat_queue_5388");
                            System.out.println(len);
                        } finally {
                            RedisClient.closeRedis(jedis);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }


    private static class OvertimePubSub extends JedisPubSub {
        @Override
        public void onUnsubscribe(String channel, int subscribedChannels) {
            System.out.println("un sub");
        }

        @Override
        public void onSubscribe(String channel, int subscribedChannels) {
            System.out.println("sub");
        }

        @Override
        public void onPUnsubscribe(String pattern, int subscribedChannels) {
        }

        @Override
        public void onPSubscribe(String pattern, int subscribedChannels) {
        }

        @Override
        public void onPMessage(String pattern, String channel, String message) {
        }

        @Override
        public void onMessage(String channel, String message) {
            this.unsubscribe();
        }

    }
}
