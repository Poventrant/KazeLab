package kaze.algorithm;

import java.util.Scanner;

/**
 * Created by 枫叶 on 2016/3/31.
 */
public class PrecisionMultiplication {

    public static void main(String[] args) {

        char[] as, bs;
        Scanner input = new Scanner(System.in);
        as = input.next().toCharArray();
        bs = input.next().toCharArray();

        int ai[] = new int[as.length];
        int bi[] = new int[bs.length];
        int resi[] = new int[as.length + bs.length];

        int alen = as.length, blen = bs.length;
        for (int i = 0; i < alen; i++) ai[alen - i - 1] = as[i] - '0';
        for (int i = 0; i < blen; i++) bi[blen - i - 1] = bs[i] - '0';

        for (int i = 0; i < alen; i++) {
            for (int j = 0; j < blen; j++) {
                resi[i + j] += ai[i] * bi[j];
                resi[i + j + 1] += resi[i + j] / 10;
                resi[i + j] %= 10;
            }
        }
        int reslen = alen + blen - 1;
        while (resi[reslen] == 0 && reslen > 1) reslen--;
        for (int i = reslen; i >= 0; i--) {
            System.out.print(resi[i]);
        }
        System.out.println();
    }
}
