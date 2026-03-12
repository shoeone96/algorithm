package y2026.mar.week1;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottenOrange {
    private int answer = 0;
    private static final int[][] go = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isOfferedAny = false;
            for (int i = 0; i < size; i++) {
                int[] rottenOrange = queue.poll();
                for (int k = 0; k < go.length; k++) {
                    int newRow = rottenOrange[0] + go[k][0];
                    int newCol = rottenOrange[1] + go[k][1];
                    if (isValidated(newRow, newCol, grid) && grid[newRow][newCol] != 2 && grid[newRow][newCol] != 0) {
                        grid[newRow][newCol] = 2;
                        queue.offer(new int[]{newRow, newCol});
                        isOfferedAny = true;
                    }
                }
            }

            if (isOfferedAny) answer++;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return answer;
    }

    private boolean isValidated(int newRow, int newCol, int[][] grid) {
        return newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length;
    }
}
