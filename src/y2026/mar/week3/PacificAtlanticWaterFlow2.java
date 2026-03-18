package y2026.mar.week3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow2 {
    private final int[][] go = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> answer = new ArrayList<>();
        Queue<int[]> pacific = new ArrayDeque<>();
        Queue<int[]> atlantic = new ArrayDeque<>();
        int[][] visit = new int[heights.length][heights[0].length];
        boolean[][] isVisitedPacific = new boolean[heights.length][heights[0].length];
        boolean[][] isVisitedAtlantic = new boolean[heights.length][heights[0].length];

        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (i == 0 || j == 0) {
                    pacific.offer(new int[]{i, j});
                    isVisitedPacific[i][j] = true;
                }

                if (i == heights.length - 1 || j == heights[0].length - 1) {
                    atlantic.offer(new int[]{i, j});
                    isVisitedAtlantic[i][j] = true;
                }
            }
        }

        bfs(pacific, heights, visit, answer, isVisitedPacific);
        bfs(atlantic, heights, visit, answer, isVisitedAtlantic);

        return answer;
    }

    private void bfs(Queue<int[]> queue, int[][] heights, int[][] visit, List<List<Integer>> answer, boolean[][] isVisited) {
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            visit[poll[0]][poll[1]]++;
            if (visit[poll[0]][poll[1]] == 2) {
                answer.add(List.of(poll[0], poll[1]));
            }

            for (int i = 0; i < go.length; i++) {
                int newRow = poll[0] + go[i][0];
                int newCol = poll[1] + go[i][1];

                if (isValidated(newRow, newCol, heights) && !isVisited[newRow][newCol] && heights[newRow][newCol] >= heights[poll[0]][poll[1]]) {
                    isVisited[newRow][newCol] = true;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }
    }

    private boolean isValidated(int newRow, int newCol, int[][] heights) {
        return newRow >= 0 && newRow < heights.length && newCol >= 0 && newCol < heights[0].length;
    }
}
