package y2026.mar.week3;

import java.util.ArrayDeque;
import java.util.Queue;

public class AsFarFromLandAsPossible {
    private final int[][] go = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public int maxDistance(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();
        int answer = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < go.length; i++) {
                int newRow = poll[0] + go[i][0];
                int newCol = poll[1] + go[i][1];

                if (isValidated(newRow, newCol, grid) && grid[newRow][newCol] == 0) {
                    grid[newRow][newCol] = grid[poll[0]][poll[1]] + 1;
                    if (grid[newRow][newCol] > answer) {
                        answer = grid[newRow][newCol];
                    }
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return answer - 1;
    }

    private boolean isValidated(int newRow, int newCol, int[][] grid) {
        return newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length;
    }
}
