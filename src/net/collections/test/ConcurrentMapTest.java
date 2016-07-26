package net.collections.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by 枫叶 on 2016/3/20.
 */
public class ConcurrentMapTest {
    static String test = "????????";
    public static void main(String[] args) {
        ConcurrentMap<String ,Object> map = new ConcurrentHashMap<String, Object>(2);
//        HashMap<String, Object> map = new HashMap<String, Object>(3);
        int i = 10;
        map.put("pwq", 123);
        map.put("pao", 123);
        map.put("kaze", 123);
        System.out.println(map.size());
        map.put("wen", 123);
        map.put("quan", 123);
        map.put("kaze", 321);

        for (Map.Entry<String, Object> e : map.entrySet()) {
            System.out.println(e.getKey());
        }
    }
}
