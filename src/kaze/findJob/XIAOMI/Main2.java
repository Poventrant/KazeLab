package kaze.findJob.XIAOMI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main2 {

//（"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"）

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                byte[] mask = new byte[27];
                List<Integer> result = new ArrayList<>();
                String line = in.next();
                for (int j = 0; j < line.length(); j++) {
                    ++mask[line.charAt(j) - 'A'];
                }
                //0
                if (mask['Z' - 'A'] > 0) {
                    int times = mask['Z' - 'A'];
                    for (int j = 0; j < times; j++) {
                        --mask['Z' - 'A'];
                        --mask['E' - 'A'];
                        --mask['R' - 'A'];
                        --mask['O' - 'A'];
                        result.add(2);
                    }
                }
                //2
                if (mask['W' - 'A'] > 0) {
                    int times = mask['W' - 'A'];
                    for (int j = 0; j < times; j++) {
                        --mask['T' - 'A'];
                        --mask['W' - 'A'];
                        --mask['O' - 'A'];
                        result.add(4);
                    }
                }
                //8
                if (mask['G' - 'A'] > 0) {
                    int times = mask['G' - 'A'];
                    for (int j = 0; j < times; j++) {
                        --mask['E' - 'A'];
                        --mask['I' - 'A'];
                        --mask['G' - 'A'];
                        --mask['H' - 'A'];
                        --mask['T' - 'A'];
                        result.add(0);
                    }
                }
                //3
                if (mask['H' - 'A'] > 0) {
                    int times = mask['H' - 'A'];
                    for (int j = 0; j < times; j++) {
                        --mask['T' - 'A'];
                        --mask['H' - 'A'];
                        --mask['R' - 'A'];
                        --mask['E' - 'A'];
                        --mask['E' - 'A'];
                        result.add(5);
                    }
                }
                //4
                if (mask['R' - 'A'] > 0) {
                    int times = mask['R' - 'A'];
                    for (int j = 0; j < times; j++) {
                        --mask['F' - 'A'];
                        --mask['O' - 'A'];
                        --mask['U' - 'A'];
                        --mask['R' - 'A'];
                        result.add(6);
                    }
                }
                //6
                if (mask['X' - 'A'] > 0) {
                    int times = mask['X' - 'A'];
                    for (int j = 0; j < times; j++) {
                        --mask['S' - 'A'];
                        --mask['I' - 'A'];
                        --mask['X' - 'A'];
                        result.add(8);
                    }
                }
                //1
                if (mask['O' - 'A'] > 0) {
                    int times = mask['O' - 'A'];
                    for (int j = 0; j < times; j++) {
                        --mask['O' - 'A'];
                        --mask['N' - 'A'];
                        --mask['E' - 'A'];
                        result.add(3);
                    }
                }
                //5
                if (mask['F' - 'A'] > 0) {
                    int times = mask['F' - 'A'];
                    for (int j = 0; j < times; j++) {
                        --mask['F' - 'A'];
                        --mask['I' - 'A'];
                        --mask['V' - 'A'];
                        --mask['E' - 'A'];
                        result.add(7);
                    }
                }
                //7
                if (mask['S' - 'A'] > 0) {
                    int times = mask['S' - 'A'];
                    for (int j = 0; j < times; j++) {
                        --mask['S' - 'A'];
                        --mask['E' - 'A'];
                        --mask['V' - 'A'];
                        --mask['E' - 'A'];
                        --mask['N' - 'A'];
                        result.add(9);
                    }
                }
                //9
                if (mask['I' - 'A'] > 0) {
                    int times = mask['I' - 'A'];
                    for (int j = 0; j < times; j++) {
                        --mask['N' - 'A'];
                        --mask['I' - 'A'];
                        --mask['N' - 'A'];
                        --mask['E' - 'A'];
                        result.add(1);
                    }
                }
                Collections.sort(result);
                for (Integer r : result) {
                    System.out.print(r);
                }
                System.out.println();
            }
        }
    }
}
