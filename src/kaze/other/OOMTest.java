package kaze.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by kaze on 2016/8/4.
 */
public class OOMTest {
    /**
20
-2 0 -3 3 -3 -4 -3 1 3 3 -2 0 3 2 2 -5 1 -2 -3 -5
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int lcount = 0;
        List<Integer> ol = new ArrayList<>();
        int sum = 0;
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            int ser = input.nextInt();
            sum += ser;
            if(ser % 5 == 0) {
                lcount += ser;
            } else if(ser % 3 != 0) {
                ol.add(ser);
            }
        }
        if(sum % 2 != 0) {
            System.out.println(false);
        } else {
            dfs(lcount, sum / 2, ol, 0);
            System.out.println(false);
        }
    }

    private static void dfs(int lc, int target, List<Integer> ol, int index) {
        if (lc == target) {
            System.out.println(true);
            System.exit(0);
        } else {
            for (int i = index; i < ol.size(); i++) {
                if(lc + ol.get(i) <= target) {
                    dfs(lc + ol.get(i), target, ol, i + 1);
                }
            }
        }
    }
}
