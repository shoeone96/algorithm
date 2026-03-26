package y2026.mar.week4;

public class BalancedBinaryTree {
    boolean answer = true;

    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return answer;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = dfs(root.left);
        int rightDepth = dfs(root.right);

        if (Math.abs(leftDepth - rightDepth) > 1) {
            answer = false;
        }

        return Integer.max(leftDepth, rightDepth) + 1;
    }
}
