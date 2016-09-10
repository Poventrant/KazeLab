package kaze.other;

import java.util.Scanner;

/**
 * Created by kaze on 2016/9/10.
 */
public class NOWCODER_RABBIT_BIRTH {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int m = in.nextInt();
            System.out.println(getit(m));
        }
    }

    private static int getit(int m) {
        if(m < 3) return 1;
        return getit(m-1) + getit(m-2);
    }
}
