package kaze.other;


import java.util.Scanner;

/**
 * Created by kaze on 2016/8/9.
 */
public class NET_EASE_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String addend = scanner.next();
            String augend = scanner.next();
            if(addend == null || augend == null
                    || addend.length() == 0 || augend.length() == 0) continue;
            java.math.BigInteger bi = new java.math.BigInteger(scanner.next());
            System.out.println(bi.add(new java.math.BigInteger("")).toString());
        }
    }
}
