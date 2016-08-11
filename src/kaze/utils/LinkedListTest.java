package kaze.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kaze on 2016/8/10.
 */
public class LinkedListTest {
    public static void main(String[] args) {
        final List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    list.add(i);
                }
            }
        }).start();
        for (Integer i : list ) {
            System.out.println(i);
        }
    }
}
