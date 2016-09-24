package kaze.findJob.XIAOMI;

import java.util.*;

/**
 * Created by kaze on 2016/9/23.
 */
public class Main3 {

    static int _max_;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int times = in.nextInt() - 1;
            if(times <= 0) {
                System.out.println(0);
                continue;
            }
            Map<Integer, List<Integer>> con = new HashMap<>();
            boolean[] map = new boolean[times + 1];
            for (int i = 0; i < times; i++) {
                int p = in.nextInt();
                int c = in.nextInt();
                map[p] = true;
                List<Integer> list = con.get(p);
                if(list == null) {
                    list = new ArrayList<>();
                    list.add(c);
                    con.put(p, list);
                } else {
                    list.add(c);
                }
            }
            _max_ = 1;
            for (int i = 0; i < map.length; i++) {
                if(map[i]) {
                    travel(con, i, 1);
                }
            }
            System.out.println(_max_);
        }
    }

    private static void travel(Map<Integer, List<Integer>> con, int parent, int path) {
        List<Integer> list = con.get(parent);
        if(list == null) {
            _max_ = Math.max(_max_, path);
        } else {
            for(Integer i : list) {
                travel(con, i, path+1);
            }
        }
    }
}
