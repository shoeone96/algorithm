package y2026.mar.week4;

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        TreeNode left = node.left;
        TreeNode right = node.right;

        node.right = left;
        node.left = right;

        if (left != null) {
            dfs(left);
        }

        if (right != null) {
            dfs(right);
        }
    }
}
