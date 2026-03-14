package y2026.mar.week1;

import java.util.ArrayDeque;
import java.util.Queue;

public class MatrixOne2 {
    private final int[][] go = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int[][] updateMatrix(int[][] mat) {
        boolean[][] isVisited = new boolean[mat.length][mat[0].length];
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] answer = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j, 0});
                    isVisited[i][j] = true;
                }
            }
        }

        bfs(queue, mat, answer, isVisited);
        return answer;
    }

    private void bfs(Queue<int[]> queue, int[][] mat, int[][] answer, boolean[][] isVisited) {
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < go.length; i++) {
                int newRow = poll[0] + go[i][0];
                int newCol = poll[1] + go[i][1];
                if (isValidate(newRow, newCol, mat) && !isVisited[newRow][newCol]) {
                    if (mat[newRow][newCol] == 1) {
                        int distance = poll[2] + 1;
                        if (answer[newRow][newCol] == 0 || answer[newRow][newCol] > distance) {
                            answer[newRow][newCol] = distance;
                        }
                    }

                    isVisited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol, poll[2] + 1});
                }
            }
        }
    }

    private boolean isValidate(int newRow, int newCol, int[][] mat) {
        return newRow >= 0 && newCol >= 0 && newRow < mat.length && newCol < mat[0].length;
    }
}

