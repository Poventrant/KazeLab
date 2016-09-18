package kaze.huaweioj;

import java.util.*;

public class Main {

    static boolean vis[] = new boolean[105];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> evens = new ArrayList<>(n),
                odds = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int temp = in.nextInt();
            if ((temp & 1) == 0) {
                evens.add(temp);
            } else odds.add(temp);
        }
        int pre[] = new int[105];
        for (int i = 0; i < evens.size(); i++) {
            Arrays.fill(vis, false);
            xyl(evens.get(i), odds, pre);
        }
        int count = 0;
        for (int j = 0; j < pre.length; j++) {
            if (pre[j] != 0) {
                ++count;
            }
        }
        System.out.println(count);
    }

    private static boolean xyl(final int idx, final List<Integer> odds, int[] pre) {
        for (int i = 0; i < odds.size(); i++) {
            if (isPrime(idx + odds.get(i)) && !vis[i]) {
                vis[i] = true;
                if (pre[i] == 0 || xyl(pre[i], odds, pre)) {
                    pre[i] = idx;
                    return true;
                }
            }
        }
        return false;
    }


    static Map<Integer, Boolean> primeMap = new HashMap<>();

    private static boolean isPrime(int n) {
        Boolean flag = primeMap.get(n);
        if (flag == null) {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    primeMap.put(n, false);
                    return false;
                }
            }
            primeMap.put(n, true);
            return true;
        } else return flag;
    }

}
/*
5
4 2 5 6 13

38
25337 2817 17946 4973 13973 27161 11729 26509 21925 20578 25824 26728 7609 19330 13841 27168 21751 17414 28070 6368 21303 15087 5428 9005 20132 13445 19423 21448 509 15483 24242 23492 20717 14190 7808 9363 278 24852

---18
 */