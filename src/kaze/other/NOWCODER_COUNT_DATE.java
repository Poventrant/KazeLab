package kaze.other;

import java.util.Scanner;

/**
 * Created by kaze on 2016/9/10.
 */
public class NOWCODER_COUNT_DATE {

    final static int monthDay[] = {
            0,31,59,90,120,151,181,212,243,273,304,334,365
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int year = in.nextInt();
            int month = in.nextInt();
            int day = in.nextInt();
            boolean isRun = (year % 100 == 0) ? year % 4 == 0 : year % 4 == 0;
            int days = monthDay[month-1] + day;
            if(month > 2 && isRun) ++days;
            System.out.println(days);
        }
    }
}
