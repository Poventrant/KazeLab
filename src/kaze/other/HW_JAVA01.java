package kaze.other;

import java.util.*;


/**
 http://www.nowcoder.com/practice/9af744a3517440508dbeb297020aca86?tpId=37&tqId=21316&rp=&ru=/ta/huawei&qru=/ta/huawei/question-ranking
20
-2 0 -3 3 -3 -4 -3 1 3 3 -2 0 3 2 2 -5 1 -2 -3 -5
 22
 2 -1 2 -4 -3 0 2 -5 -5 -4 5 -2 2 2 5 -5 -4 -1 -2 -1 -4 -3
 7
 3 -4 -1 2 -4 -5 5
 */
public class HW_JAVA01 {

    static boolean flag = false;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNextInt()) {
            int lcount = 0;
            List<Integer> ol = new ArrayList<>();
            int sum = 0, n = 0;
            n = input.nextInt();
            int time = 0;
            while (input.hasNextInt() && time ++ < n) {
                int ser = input.nextInt();
                sum += ser;
                if(ser % 5 == 0) {
                    lcount += ser;
                } else if(ser % 3 != 0) {
                    ol.add(ser);
                }
                if(time >= n) break;
            }
            if(sum % 2 != 0) {
                System.out.println(false);
            } else {
                flag = false;
//                Collections.sort(ol);
                dfs(lcount, sum / 2, ol, 0);
                System.out.println(flag);
            }
        }
    }

    static void dfs(int lc, int target, List<Integer> ol, int index) {
        if(flag) return;
        if (lc == target) {
            flag = true;
        } else {
            for (int i = index; i < ol.size(); i++) {
//                if(Math.abs(target - (lc + ol.get(i))) <= Math.abs(target - lc)) {
                    dfs(lc + ol.get(i), target, ol, i + 1);
//                }
            }
        }
    }
    
}