package kaze.algorithm;

import java.util.Scanner;

public class Main {
    static volatile char flag = 'A';

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            final int n = in.nextInt();
            flag = 'A';
            for (int i = 0; i < 4; i++) {
                final int finalI = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 0; j < n; j++) {
                            char temp = (char) ('A' + finalI);
                            while (flag != temp) ;
                            System.out.print((char) ('A' + finalI));
                            flag = (char) (temp + 1);
                            if (flag == 'E') flag = 'A';
                        }
                    }
                }).start();
            }
        }
    }
}