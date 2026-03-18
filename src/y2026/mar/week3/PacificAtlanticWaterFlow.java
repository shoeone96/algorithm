package y2026.mar.week3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow {
    private final int[][] go = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (bfs(i, j, heights)) {
                    answer.add(new ArrayList<>(List.of(i, j)));
                }
            }
        }

        return answer;
    }

    private boolean bfs(int row, int col, int[][] heights) {
        boolean isPacific = false;
        boolean isAtlantic = false;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[heights.length][heights[0].length];
        isVisited[row][col] = true;
        queue.offer(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (poll[0] == 0 || poll[1] == 0) isPacific = true;
            if (poll[0] == heights.length - 1 || poll[1] == heights[0].length - 1) isAtlantic = true;

            for (int i = 0; i < go.length; i++) {
                int newRow = poll[0] + go[i][0];
                int newCol = poll[1] + go[i][1];

                if (isValidated(newRow, newCol, heights) && !isVisited[newRow][newCol] && heights[newRow][newCol] < heights[poll[0]][poll[1]]) {
                    isVisited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return isAtlantic && isPacific;
    }

    private boolean isValidated(int row, int col, int[][] heights) {
        return row >= 0 && row < heights.length && col >= 0 && col < heights[0].length;
    }
}
