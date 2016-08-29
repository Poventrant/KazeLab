package kaze.other;

import java.util.ArrayList;

/**
 * Created by kaze on 2016/8/28.
 */
public class ContinousSum {

    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (sum < 3) return list;
        int s = (int) Math.sqrt(2 * sum);
        for (int i = s; i >= 2; i--) {
            if (2 * sum % i == 0) {
                int d = 2 * sum / i;
                //一个奇数一个偶数
                if (d % 2 == 0 && i % 2 != 0 || d % 2 != 0 && i % 2 == 0) {
                    int a1 = (d - i + 1) / 2;
                    int an = (d + i - 1) / 2;
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (int j = a1; j <= an; j++)
                        temp.add(j);
                    list.add(temp);
                }
            }
        }
        return list;

    }

    public static void main(String[] args) {
        FindContinuousSequence(100);
    }
}
