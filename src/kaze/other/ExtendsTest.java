package kaze.other;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kaze on 2016/7/19.
 */
public class ExtendsTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("dfsad", "fg3542", "23423ghbfdc");
        get(list);
    }

    public static void get(List<?> list) {
        for (Object o : list) {
            if(o instanceof String) {
                System.out.println(o);
            }
        }
    }
}
