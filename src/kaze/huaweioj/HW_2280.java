package kaze.huaweioj;

import java.util.Scanner;

/**
 * Created by kaze on 2016/9/15.
 */
public class HW_2280 {

    //10 5 2 1
    static int moneyStore[] = new int[4];
    static int []store;

    static int tuibi(int money) {
        int m10 = 0, m5 = 0, m2 = 0, m1 = 0;
        if(moneyStore[0] > 0) {
            m10 = (int) (money / 10);
            if(m10 > moneyStore[0]) m10 = moneyStore[0];
            money -= 10 * m10;
        }
        if(moneyStore[1] > 0) {
            m5 = (int) (money / 5);
            if(m5 > moneyStore[1]) m5 = moneyStore[1];
            money -= 5 * m5;
        }
        if(moneyStore[2] > 0) {
            m2 = (int) (money / 2);
            if(m2 > moneyStore[2]) m2 = moneyStore[2];
            money -= 2 * m2;
        }
        if(moneyStore[3] > 0) {
            m1 = (int) money;
            if(m1 > moneyStore[3]) m1 = moneyStore[3];
            money -= m1;
        }
        moneyStore[0] -= m10;
        moneyStore[1] -= m5;
        moneyStore[2] -= m2;
        moneyStore[3] -= m1;
        return money;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String commands[] = in.nextLine().split(";");
        for(String commond : commands) {
            if(commond.startsWith("r")) {
                String cs[] = commond.split(" ");
                String c1[] = cs[1].split("-");
                store = new int[c1.length];
                for (int i = 0; i < c1.length; i++) {
                    store[i] = Integer.valueOf(c1[i]);
                }
                c1 = cs[2].split("-");
                for (int i = 0; i < 4; i++) {
                    moneyStore[i] = Integer.parseInt(c1[i]);
                }
                System.out.println("S001:Initialization is successful");
            } else if(commond.startsWith("p")) {
                int m = Integer.parseInt(commond.split(" ")[1]);
                if(m == 1 || m == 2 || m == 5 || m == 10) {

                } else {
                    System.out.println("E002:Denomination error");
                }
            } else if(commond.startsWith("b")) {

            } else if(commond.startsWith("c")) {

            } else if(commond.startsWith("q")) {

            }
        }
    }
}
