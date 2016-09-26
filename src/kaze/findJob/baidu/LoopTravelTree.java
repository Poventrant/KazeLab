package kaze.findJob.baidu;

import java.util.*;

/**
 * Created by kaze on 2016/9/25.
 */
public class LoopTravelTree {
    static class Node {
        int val;
        Node left = null;
        Node right = null;
    }

    public boolean preOrderTraverse(Node root) {
        if (root == null) return true;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {//开始下一次从右上到左下的循环
            Node node = stack.pop();
            while (node != null) {
                System.out.println(node.val);
                if (node.right != null)//有右孩子，就入栈
                    stack.push(node.right);
                node = node.left;
            }
        }
        return true;
    }

    /**
     *
     * @param root 树的根节点
     * 利用栈模拟递归过程实现循环先序遍历二叉树
     * 这种方式具备扩展性，它模拟递归的过程，将左子树点不断的压入栈，直到null，然后处理栈顶节点的右子树
     */
    public static void preOrderStack_2(Node root) {
        if (root == null) return;
        Stack<Node> s = new Stack<>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                System.out.println(root.val);
                s.push(root);//先访问再入栈
                root = root.left;
            }
            root = s.pop();
            root = root.right;//如果是null，出栈并处理右子树
        }
    }


    /**
     * @param root 树根节点
     *             利用栈模拟递归过程实现循环中序遍历二叉树
     *             思想和上面的preOrderStack_2相同，只是访问的时间是在左子树都处理完直到null的时候出栈并访问。
     */
    public static void inOrderStack(Node root) {
        if (root == null) return;
        Stack<Node> s = new Stack<>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                s.push(root);//先访问再入栈
                root = root.left;
            }
            root = s.pop();
            System.out.println(root.val);
            root = root.right;//如果是null，出栈并处理右子树
        }
    }

    void postOrder(Node root) {
        if (root == null) return;
        Node p = root;
        Stack<Node> stack = new Stack<>();
        stack.push(p);
        stack.push(p);
        while (!stack.empty()) {
            p = stack.pop();
            if (!stack.empty() && p == stack.peek()) {
                if (p.right != null) {
                    stack.push(p.right);
                    stack.push(p.right);
                }
                if (p.left != null) {
                    stack.push(p.left);
                    stack.push(p.left);
                }
            } else {
                System.out.println(p.val);
            }
        }
    }

    /**
     * @param root 树根节点
     *             层序遍历二叉树，用队列实现，先将根节点入队列，只要队列不为空，然后出队列，并访问，接着讲访问节点的左右子树依次入队列
     */
    public static void levelTravel(Node root) {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node temp = q.poll();
            System.out.println(temp.val);
            if (temp.left != null) q.add(temp.left);
            if (temp.right != null) q.add(temp.right);
        }
    }

    public static void main(String[] args) {

    }

}
