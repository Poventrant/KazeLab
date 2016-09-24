package kaze.findJob.XIAOMI;

import java.util.Scanner;

public class Main1 {

    static void reverse(char [] arr, int start, int end) {
        int l = start, h = end;
        while(l < h) {
            char temp = arr[l];
            arr[l] = arr[h];
            arr[h] = temp;
            ++l;
            --h;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            char[] line = in.nextLine().toCharArray();
            reverse(line, 0, line.length-1);
            int start = 0;
            for (int i = 0; i < line.length; i++) {
                if(i + 1 == line.length ||
                        (i + 1 < line.length && line[i+1] == ' ')) {
                    reverse(line, start, i);
                    start = i + 2;
                }
            }
            System.out.println(line);
        }
    }
}
