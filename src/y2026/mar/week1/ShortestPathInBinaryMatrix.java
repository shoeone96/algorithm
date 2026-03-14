package y2026.mar.week1;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    private final int[][] go = {{1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1)
            return -1;
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0, 1});
        isVisited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (poll[0] == grid.length - 1 && poll[1] == grid[0].length - 1) {
                return poll[2];
            }

            for (int i = 0; i < go.length; i++) {
                int newRow = poll[0] + go[i][0];
                int newCol = poll[1] + go[i][1];
                if (isValidated(newRow, newCol, grid) && grid[newRow][newCol] == 0 && !isVisited[newRow][newCol]) {
                    isVisited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol, poll[2] + 1});
                }
            }
        }

        return -1;
    }

    private boolean isValidated(int newRow, int newCol, int[][] grid) {
        return newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length;
    }
}
