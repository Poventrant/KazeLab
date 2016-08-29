package kaze.other;

import java.util.Scanner;
/*
1900 1 1 是星期一，求输入在这个之后的日期是星期几
 */
public class WEEK_190011 {

    final static int months[] = new int[] {
         0, 31,59, 90,120,151,181,212,243,273,304,334,365
          /*31,28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31*/
    };

    public static int hasNRunNian(int year) {
        int interval = year - 1900;
        if(interval <= 0) return 0;
        int count = interval / 4;
        //因为1900 + 100 = 2000 就是闰年
        int hundred = interval - 100;
        if(hundred > 0) {
            //有多少个世纪年
            int temp = hundred / 100;
            //世纪年中能被4整除的才是闰年
            count = count - temp + temp / 4;
        }
        return count;
    }

    public static int countDays(int year, int month, int day) {
        int days = (year - 1900) * months[12] + months[month-1] + day;
        days = days + hasNRunNian(year-1);
        if( ( (year % 100 == 0 && year % 400 == 0)
                || (year % 100 != 0 && year % 4 == 0) )
                && month > 2) {
            ++days;
        }
        return days;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int year = scanner.nextInt();
            int month = scanner.nextInt();
            int day = scanner.nextInt();
            int res;
            System.out.println((res = countDays(year, month, day) % 7) == 0 ? 7 : res);
        }
    }
}
