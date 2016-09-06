package kaze.other;

import java.util.Scanner;
/*
http://www.nowcoder.com/practice/f821a39207cd43518ccddb27fee0481d?tpId=49&tqId=29330&rp=1&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_BATTERY {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Pointer p0 = new Pointer(),
                p1 = new Pointer(),
                p2 = new Pointer(),
                p3 = new Pointer();
        while(in.hasNextInt()) {
            try {
                int r = in.nextInt();
                p1.x = in.nextInt();
                p1.y = in.nextInt();
                p2.x = in.nextInt();
                p2.y = in.nextInt();
                p3.x = in.nextInt();
                p3.y = in.nextInt();

                p0.x = in.nextInt();
                p0.y = in.nextInt();
                int dist = getDist(p1, p0, r)
                        + getDist(p2, p0, r)
                        + getDist(p3, p0, r);
                System.out.println(dist + "x");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getDist(Pointer p1, Pointer p0, int r) {
        double dist = Math.sqrt(Math.pow(Math.abs(p1.x-p0.x),2)
                + Math.pow(Math.abs(p1.y-p0.y),2));
        return Double.compare(r, dist) >= 0 ? 1 : 0;
    }

    private static class Pointer {
        int x;
        int y;
    }
}
