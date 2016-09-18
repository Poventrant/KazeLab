package kaze.didi;

import java.util.Scanner;

public class Zero {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int count = 0;
            for (int i = 0; i < n; i++) {
                int temp = i+1;
                while (temp % 5 == 0) {
                    temp /= 5;
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
