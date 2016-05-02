package kaze.poj;

import java.lang.*;
import java.math.*;
import java.util.*;

public class Problem1000 {
    public static void main(String [] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        if(n <= 0) throw new Exception("n <= 0");
        char [] result = new char[n+1];
        for (int i = 0; i < 10; i++) {
            result[0] = (char) (i + '0');
            recurse(result, n, 0);
        }

       /* BigDecimal bi = new BigDecimal(1);
        BigDecimal ten = new BigDecimal(10);
        for (int i = 0; i < n; i++) {
            bi = bi.multiply(ten);
        }

        BigDecimal max = bi.add(new BigDecimal(-1));

        BigDecimal result = new BigDecimal(1);
        BigDecimal one = new BigDecimal(1);
        while (result.compareTo(bi) != 0) {
            result = result.add(one);
            System.out.println(result);
        }*/

       /* int count = 0;
        int n = 7;
        while(n != 0) {
            n = (n-1)&n;
            ++ count;
        }
        System.out.printf("%d", count);*/
    }

    private static void recurse(char[] result, int length, int index) {
        if(index == length - 1) {
            System.out.println(result);
            return;
        }
        for (int i = 0; i < 10; i++) {
            result[index+1] = (char) (i +  '0');
            recurse(result, length, index+1);
        }
    }


}