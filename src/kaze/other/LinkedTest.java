package kaze.other;

import java.util.*;
import java.math.*;
import java.util.concurrent.*;

/**
 * Created by 枫叶 on 2016/4/20.
 */
public class LinkedTest {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(list.get(i));
        }

        Queue<Integer> queue = new LinkedBlockingQueue<Integer>();

        for (int i = 0; i < 10; ++ i) {
            queue.offer(i);
        }

        for (int i = 0; i < 10; ++ i) {
            System.out.println(queue.poll());
        }

        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < 10; ++ i) {
            stack.push(i);
        }
        for (int i = 0; i < 10; ++ i) {
            System.out.println(stack.pop());
        }

        BigDecimal big = new BigDecimal(1);
        System.out.println(big);

        String A = "ABCDEFGH";
        int n = 8, p = 4;
        System.out.println(A.substring(p+1, n) + A.substring(0, p+1));


        System.out.println(getLongestPalindrome("abc1234321ab", 12));

    }


    public static int getLongestPalindrome(String A, int n) {
        if(n == 0) return 0;
        for(int len = n; len > 0; --len) {
            for(int s = 0; s + len <= n; ++ s) {
                String temp = A.substring(s, s+len);
                if(isPalindrome(temp)) {
                    return temp.length();
                }
            }
        }
        return 1;
    }

    public static boolean isPalindrome(String A) {
        int size = A.length();
        int len = size / 2;
        for(int i = 0; i < len; ++ i) {
            if(A.charAt(i) != A.charAt(size-i-1)) return false;
        }
        return true;
    }
}
