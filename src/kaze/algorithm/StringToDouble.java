package kaze.algorithm;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by kaze on 2016/9/19.
 */
public class StringToDouble {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.next();
            int point = line.indexOf(".");
            int sum = 0, base =1;
            for (int i = point-1; i >= 0; --i) {
                sum += (line.charAt(i) - '0') * base;
                base *= 10;
            }
            double l = 0;
            double lbase = 0.1;
            StringBuilder fstr = new StringBuilder("#0");
            if(point != -1) {
                fstr.append('.');
            }
            for (int i = point+1; i < line.length(); i++) {
                l += (line.charAt(i) - '0') * lbase;
                lbase *= 0.1;
                fstr.append('0');
            }
            DecimalFormat format = new DecimalFormat(fstr.toString());
            System.out.println(format.format(sum + l));
        }
    }
}
