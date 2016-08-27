package kaze.huaweioj;

import java.util.Scanner;

/**
 * Created by kaze on 2016/8/13.
 */
public class HW_2290 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String strs []= line.split(" +");
        System.out.println(strs[strs.length - 1].length());
    }
}
