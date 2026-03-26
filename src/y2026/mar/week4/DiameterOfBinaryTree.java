package y2026.mar.week4;

public class DiameterOfBinaryTree {
    int answer = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return answer;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftSum = dfs(root.left);
        int rightSum = dfs(root.right);
        answer = Integer.max(answer, leftSum + rightSum);
        return Math.max(leftSum, rightSum) + 1;
    }
}
