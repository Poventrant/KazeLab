package kaze.findJob.HW;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_3_2 {

    static class Pair {
        String str1, str2;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Pair p = new Pair();
            p.str1 = in.next();
            p.str2 = in.next();
            list.add(p);
        }
        for (Pair p : list) {
            if (p.str1.charAt(p.str1.length() - 1) == p.str2.charAt(0)) {
                System.out.println(p.str2.length() + 3);
            } else {
                System.out.println(p.str2.length() + 4);
            }
        }
    }
}