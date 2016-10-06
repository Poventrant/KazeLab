package kaze.algorithm;

/**
 * Created by kaze on 2016/10/4.
 */
public class SearchBinaryTree {

    private Node root;

    private Node create(int arr[]) {
        for (int i : arr) {
            if (root == null) {
                root = new Node(i);
            } else {
                insert(root, i);
            }
        }
        return root;
    }

    private void insert(Node node, int val) {
        if (node == null) return;
        if (val < node.val) {
            if (node.left == null) {
                node.left = new Node(val);
            } else {
                insert(node.left, val);
            }
        } else if (val > node.val) {
            if (node.right == null) {
                node.right = new Node(val);
            } else {
                insert(node.right, val);
            }
        } else {
            throw new RuntimeException("same value");
        }
    }

    private boolean delete(Node node, int val) {
        if (node == null) return false;
        if (val < node.val) {
            return delete(node.left, val);
        } else if (val > node.val) {
            return delete(node.right, val);
        } else {
            if (node.left == null) {

            } else if (node.right == null) {

            } else {
                Node rc = node.right;
                while (rc.left != null) {
                    rc = rc.left;
                }
                node.val = rc.val;
                delete(node.right, rc.val);
            }
        }
        return true;
    }

    private void delete(int val) {
        delete(root, val);
    }

    public static void main(String[] args) {

    }

    private static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(int val) {
            this.val = val;
        }
    }
}
