package kaze.findJob.baidu;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kaze on 2016/9/26.
 * 层次遍历树，输出指定的层节点
 */
public class TreeLevelTravel {
    private static class Node {
        int val;
        Node left = null;
        Node right = null;

        Node(int val) {
            this.val = val;
        }
    }

    /**
     * 输入第l层的节点
     *
     * @param root 树根
     * @param l    指定层数
     */
    private static void level(Node root, int l) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int levelCount = 1, level = 1;
        while (!queue.isEmpty()) {
            int count = 0;
            for (int i = 0; i < levelCount; i++) {
                Node r = queue.poll();
                if (level == l) {
                    System.out.println(r.val);
                }
                if (r.left != null) {
                    queue.add(r.left);
                    ++count;
                }
                if (r.right != null) {
                    queue.add(r.right);
                    ++count;
                }
            }
            if (count != 0) {
                ++level;
            }
            levelCount = count;
        }
        System.out.println("tree level is " + level);
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        Node c1 = new Node(1);
        Node c2 = new Node(2);
        Node c3 = new Node(3);
        Node c4 = new Node(4);
        Node c5 = new Node(5);
        root.left = c1;
        root.right = c2;
        c2.left = c3;
        c2.right = c4;
        c4.left = c5;
        level(root, 3);
    }
}
