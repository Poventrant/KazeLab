package kaze.other;

import java.util.Scanner;

/**
 * Created by kaze on 2016/9/12.
 */
public class BigNumberAdd {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String a = in.next();
            String b = in.next();
            int result[] = new int[Math.max(a.length(), b.length()) + 1];
            int ai = a.length()-1, bi = b.length()-1, index = result.length - 1;
            for (int i = index; i >= 1; i--) {
//                if(a.charAt(ai) < '0' || a.charAt(ai) > '9' ||
//                        b.charAt(bi) < '0' || b.charAt(bi) > '9') throw new NumberFormatException();
                int l = ai >= 0 ? Character.getNumericValue(a.charAt(ai)) : 0,
                        r = bi >= 0 ? Character.getNumericValue(b.charAt(bi)) : 0;
                result[i] += l + r;
                result[i-1] = result[i] / 10;
                result[i] %= 10;
                --ai;
                --bi;
            }
            boolean isZero = true;
            for (int aResult : result) {
                if (isZero && aResult != 0) {
                    isZero = false;
                } else if (isZero) {
                    continue;
                }
                System.out.print(aResult);
            }
            System.out.println();
        }
    }
}
