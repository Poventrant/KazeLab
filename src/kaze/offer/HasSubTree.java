package kaze.offer;

/**
 * Created by 枫叶 on 2016/8/22.
 */
public class HasSubTree {

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        boolean flag = false;
        if (root1.val == root2.val) {
            flag = doHasSubtree(root1, root2);
        }
        if (!flag) {
            flag = HasSubtree(root1.left, root2);
        }
        if (!flag) {
            flag = HasSubtree(root1.right, root2);
        }
        return flag;
    }

    private boolean doHasSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val == root2.val) {
            return doHasSubtree(root1.left, root2.left)
                    && doHasSubtree(root1.right, root2.right);
        }
        return false;
    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
