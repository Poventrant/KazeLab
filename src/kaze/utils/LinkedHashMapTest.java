package kaze.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kaze on 2016/8/18.
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
//        Map<String, Integer> map = new HashMap<>(),
//                lmap = new LinkedHashMap<>();
//
//        map.put("kaze",0);
//        map.put("fgkaze",0);
//        map.put("agdfsgze",0);
//        map.put("khfshe",0);
//        map.put("kazbgdsbge",0);
//        map.put("azetwt",0);
//        map.put("kazetwvbnt",0);
//        map.put("bvcbwt",0);
//        map.put("tryutyzetwt",0);
//
//        lmap.put("kaze",0);
//        lmap.put("fgkaze",0);
//        lmap.put("agdfsgze",0);
//        lmap.put("khfshe",0);
//        lmap.put("kazbgdsbge",0);
//        lmap.put("azetwt",0);
//        lmap.put("kazetwvbnt",0);
//        lmap.put("bvcbwt",0);
//        lmap.put("tryutyzetwt",0);
//        for (Map.Entry<String, Integer> e : map.entrySet()) {
//            System.out.println(e.getKey());
//        }

        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("apple", "苹果");
        map.put("watermelon", "西瓜");
        map.put("banana", "香蕉");
        map.put("peach", "桃子");

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
