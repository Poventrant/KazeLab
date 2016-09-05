package kaze.algorithm;

import java.util.*;

/**
 * Created by 枫叶 on 2016/5/11.
 */
public class RomveChar {
    public static void main(String[] args) {
        StringBuilder test = new StringBuilder("They are students.");
        String remove = "aeiou";
        Map<Character, Boolean> map = new HashMap<Character, Boolean>();
        for (int i = 0; i < remove.length(); i++) {
            map.put(remove.charAt(i), true);
        }
        StringBuilder res = new StringBuilder("");
        for (int i = 0; i < test.length(); i++) {
            char ch = test.charAt(i);
            Boolean b = map.get(ch);
            if(b != null && b == true) {
                continue;
            } else {
                res.append(ch);
            }
        }
        System.out.println(res);
    }
}
