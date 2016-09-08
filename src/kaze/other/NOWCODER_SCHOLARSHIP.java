package kaze.other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 http://www.nowcoder.com/practice/cee98a512ec246a2918ea8121f7612c8?tpId=49&tqId=29308&rp=2&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_SCHOLARSHIP {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Comparator<Pair> comparator = new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.b, o2.b);
            }
        };
        while (in.hasNextInt()) {
            int n = in.nextInt(),
                    r = in.nextInt(),
                    avg = in.nextInt();
            Pair[] ps = new Pair[n];
            int current = 0;
            for (int i = 0; i < n; i++) {
                Pair p = new Pair();
                p.a = in.nextInt();
                current += p.a;
                p.b = in.nextInt();
                ps[i] = p;
            }
            Arrays.sort(ps, comparator);
            long target = avg * n, time = 0;
            for (int i = 0; current < target && i < n; i++) {
                int grade = ps[i].a;
                while (current < target && grade < r) {
                    ++grade;
                    ++current;
                    time += ps[i].b;
                }
            }
            System.out.println(time);
        }
    }

    private static class Pair {
        int a;
        int b;
    }
}
