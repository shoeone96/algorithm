package y2026.mar.week3;

import java.util.ArrayDeque;
import java.util.Queue;

public class Ambulance {
    private final int[][] go = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}, {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};

    public int solution(int[][] city) {
        if (city[0][0] == 1 || city[city.length - 1][city[0].length - 1] == 1) {
            return -1;
        }

        boolean[][] isVisited = new boolean[city.length][city[0].length];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 1});
        isVisited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (poll[0] == city.length - 1 && poll[1] == city[0].length - 1) {
                return poll[2];
            }
            for (int i = 0; i < go.length; i++) {
                int newRow = poll[0] + go[i][0];
                int newCol = poll[1] + go[i][1];
                if (isValidated(newRow, newCol, city) && !isVisited[newRow][newCol] && city[newRow][newCol] == 0) {
                    isVisited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol, poll[2] + 1});
                }
            }
        }

        return -1;
    }

    private boolean isValidated(int newRow, int newCol, int[][] city) {
        return newRow >= 0 & newRow < city.length && newCol >= 0 & newCol < city[0].length;
    }
}
