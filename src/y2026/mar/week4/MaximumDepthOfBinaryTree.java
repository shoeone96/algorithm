package y2026.mar.week4;


public class MaximumDepthOfBinaryTree {
    /**
     * From the root Node, guess the deepest level
     */
    static int answer;

    public int maxDepth(TreeNode root) {
        answer = 0;
        if (root != null) {
            dfs(root, 1);
        }
        return answer;
    }

    private void dfs(TreeNode node, int depth) {
        if (depth > answer)
            answer = depth;

        if (node.left != null) {
            dfs(node.left, depth + 1);
        }
        if (node.right != null) {
            dfs(node.right, depth + 1);
        }
    }
}

class MaximumDepthOfBinaryTree2 {
    public int maxDepth(TreeNode root) {
        int answer = 0;
        if (root != null) {
            answer = dfs(root, 1, answer);
        }
        return answer;
    }

    private int dfs(TreeNode node, int depth, int answer) {
        if (depth > answer) answer = depth;

        if (node.left != null) {
            int left = dfs(node.left, depth + 1, answer);
            if (left > answer) answer = left;
        }
        if (node.right != null) {
            int right = dfs(node.right, depth + 1, answer);
            if (right > answer) answer = right;
        }

        return answer;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
