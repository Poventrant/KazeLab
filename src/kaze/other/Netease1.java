package kaze.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Netease1 {
    private static String regex = "\\s";

    public static void main(String[] args) throws IOException {
        Set<String> set = new HashSet<>();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        for (int i = 0; i < 50; i++) {
            if (in.ready()) {
                String text = in.readLine();
                String[] arr = text.split(regex);
                for (String val : arr) {
                    set.add(val);
                }
            } else {
                break;
            }
        }
        System.out.println(set.size());
        in.close();
    }
}