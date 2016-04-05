package kaze.algorithm;

import java.util.Scanner;

/**
 * Created by 枫叶 on 2016/3/31.
 */
public class PrecisionMultiplication {

    public static void main(String[] args) {

        int ai[] = new int[10];
        int bi[] = new int[10];
        int resi[] = new int[10];

        String as, bs;
        Scanner input = new Scanner(System.in);
        as = input.next();
        bs = input.next();

        int alen = as.length(), blen = bs.length();
        for (int i = 0; i < alen; i++) ai[alen - i] = as.charAt(i) - 48;
        for (int i = 0; i < blen; i++) bi[blen - i] = bs.charAt(i) - 48;

        for(int s : ai) {
            System.out.print(s);
        }
        System.out.print("\n");
        for(int s : bi) {
            System.out.print(s);
        }
        System.out.print("\n");

        for (int i = 1; i <= alen; i++) {
            int x = 0;
            for (int j = 1; j <= blen; j++) {
                resi[i + j - 1] = ai[i] * bi[j] + x + resi[i + j - 1];
                x = resi[i + j - 1] / 10;
                resi[i + j - 1] %= 10;
            }
            resi[i + blen] = x;
        }
        int reslen = alen + blen;
        while (resi[reslen] == 0 && reslen > 1) reslen--;
        for (int i = reslen; i >= 1; i--) System.out.print(resi[i]);

    }
}
