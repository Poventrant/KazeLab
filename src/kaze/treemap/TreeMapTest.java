package kaze.treemap;

import java.util.TreeMap;

/**
 * Created by kaze on 16-3-18.
 */
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<String, Object> map = new TreeMap<String, Object>();
        map.put("pang", 123);

        map.put("kaze", 123);

        map.put("wen", 123);

        map.put("quan", 123);

        map.remove("pwq");
    }
}
