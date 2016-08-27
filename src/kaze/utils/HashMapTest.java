package kaze.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kaze on 2016/8/16.
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i, i);
        }

        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next().getKey());
            it.remove();
        }

//        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
//            System.out.println(e.getKey());
//            map.remove(e.getKey());
//        }
    }
}
