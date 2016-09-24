package kaze.findJob.HW;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String m = in.next();
        String n = in.next();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n.length(); i++) {
            set.add(n.charAt(i));
        }
        for (int i = 0; i < m.length(); i++) {
            char c = m.charAt(i);
            if(!set.contains(c)) {
                System.out.print(c);
            }
        }
        System.out.println();
    }
}
