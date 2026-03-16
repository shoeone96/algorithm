package y2026.mar.week3;

import java.util.ArrayDeque;
import java.util.Queue;

public class CountOfCloud {
    /**
     * 1은 구름이 있는 곳
     * 0은 구름이 없는 곳
     * 구름의 개수를 반환
     */
    private final int[][] go = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public int solution(int[][] sky) {
        int answer = 0;
        boolean[][] isVisited = new boolean[sky.length][sky[0].length];
        for (int i = 0; i < sky.length; i++) {
            for (int j = 0; j < sky[0].length; j++) {
                if (!isVisited[i][j] && sky[i][j] == 1) {
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i, j});
                    isVisited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] poll = queue.poll();
                        for (int k = 0; k < go.length; k++) {
                            int newRow = poll[0] + go[k][0];
                            int newCol = poll[1] + go[k][1];
                            if (isValidated(newRow, newCol, sky) && !isVisited[newRow][newCol] && sky[newRow][newCol] == 1) {
                                isVisited[newRow][newCol] = true;
                                queue.offer(new int[]{newRow, newCol});
                            }
                        }
                    }

                    answer++;
                }
            }
        }
        return answer;
    }

    private boolean isValidated(int newRow, int newCol, int[][] sky) {
        return newRow >= 0 && newRow < sky.length && newCol >= 0 && newCol < sky[0].length;
    }
}
