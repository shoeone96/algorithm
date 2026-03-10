package y2026.mar.week1;

public class NumberOfIsland2 {
    private static final int[][] go = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numIslands(char[][] grid) {
        int answer = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    answer++;
                }
            }
        }
        return answer;
    }

    private void dfs(char[][] grid, int row, int col) {
        for (int i = 0; i < go.length; i++) {
            int newRow = row + go[i][0];
            int newCol = col + go[i][1];
            if (isValidated(newRow, newCol, grid) && grid[newRow][newCol] == '1') {
                grid[newRow][newCol] = '0';
                dfs(grid, newRow, newCol);
            }
        }
    }

    private boolean isValidated(int newRow, int newCol, char[][] grid) {
        return newRow >= 0 && newCol >= 0 && newRow <= grid.length - 1 && newCol <= grid[0].length - 1;
    }
}
