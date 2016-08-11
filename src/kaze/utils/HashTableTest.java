package kaze.utils;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by kaze on 2016/8/10.
 */
public class HashTableTest {
    public static void main(String[] args) {
        Map<Integer, Object> map = new Hashtable<>(7);
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }

    }
}
