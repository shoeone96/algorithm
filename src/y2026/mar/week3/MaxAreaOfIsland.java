package y2026.mar.week3;

import java.util.ArrayDeque;
import java.util.Queue;

public class MaxAreaOfIsland {
    private final int[][] go = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public int maxAreaOfIsland(int[][] grid) {
        int answer = 0;
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !isVisited[i][j]) {
                    int count = bfs(i, j, grid, isVisited);
                    if (count > answer) answer = count;
                }
            }
        }

        return answer;
    }

    private int bfs(int row, int col, int[][] grid, boolean[][] isVisited) {
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        isVisited[row][col] = true;
        count++;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < go.length; i++) {
                int newRow = poll[0] + go[i][0];
                int newCol = poll[1] + go[i][1];

                if (isValidated(newRow, newCol, grid) && !isVisited[newRow][newCol] && grid[newRow][newCol] == 1) {
                    queue.offer(new int[]{newRow, newCol});
                    isVisited[newRow][newCol] = true;
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isValidated(int newRow, int newCol, int[][] grid) {
        return newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length;
    }
}
