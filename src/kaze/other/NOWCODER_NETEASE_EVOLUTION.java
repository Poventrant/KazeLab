package kaze.other;

import java.util.Scanner;
/*
http://www.nowcoder.com/practice/fe6c73cb899c4fe1bdd773f8d3b42c3d?tpId=49&tqId=29329&rp=1&ru=/ta/2016test&qru=/ta/2016test/question-ranking
 */
public class NOWCODER_NETEASE_EVOLUTION {

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int a = scanner.nextInt();
            int b;
            int result = a;
            for (int i = 0; i < n; i++) {
                b = scanner.nextInt();
                result += (result >= b ? b : gcd(result, b));
            }
            System.out.println(result);
        }
    }
}
