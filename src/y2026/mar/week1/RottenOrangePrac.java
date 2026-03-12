package y2026.mar.week1;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottenOrangePrac {
    private final int[][] go = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int orangesRotting(int[][] grid) {
        int answer = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        // 최초 썪은 오렌지 다 큐에 넣기
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int j = 0; j < go.length; j++) {
                    int newRow = poll[0] + go[j][0];
                    int newCol = poll[1] + go[j][1];
                    if (isValidated(newRow, newCol, grid) && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2;
                        queue.offer(new int[]{newRow, newCol});
                        flag = true;
                    }
                }
            }
            if (flag) answer++;
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
