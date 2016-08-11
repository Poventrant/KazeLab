package kaze.other;

import java.util.Scanner;

/*
 http://www.nowcoder.com/test/question/3897c2bcc87943ed98d8e0b9e18c4666?pid=260145&tid=4188455
 */
public class HW_MAX_GRADE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int stus = scanner.nextInt();
            int opts = scanner.nextInt();
            int grades[] = new int[stus];
            for (int i = 0; i < stus; i++) {
                grades[i] = scanner.nextInt();
            }
            for (int i = 0; i < opts; i++) {
                char alternative = scanner.next().charAt(0);
                int begin = scanner.nextInt();
                int end = scanner.nextInt();
                if(alternative == 'Q') {
                    System.out.println(findMax(grades, begin, end));
                } else {
                    grades[begin-1] = end;
                }
            }
        }
    }

    private static int findMax(int[] grades, int begin, int end) {
        int max = -1;
        int b = begin > end ? end : begin;
        int e = begin < end ? end : begin;
        for (int i = b; i <= e; i++) {
            if(grades[i-1] > max) max = grades[i-1];
        }
        return max;
    }
}
