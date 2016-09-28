package kaze.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by kaze on 2016/9/28.
 */
public class NOWCODER_SIMPLE_XML_PARSER {

    static class NodeTree {
        String tag;
        List<NodeTree> chidren = new ArrayList<>();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (in.hasNextLine()) {
            sb.append(in.nextLine());
        }
        Stack<NodeTree> stack = new Stack<>();
        char [] arr = sb.toString().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            
        }
    }
}
