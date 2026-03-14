package y2026.mar.week1;

import java.util.ArrayDeque;
import java.util.Queue;

public class MatrixOne {

    /**
     *
     * @param mat
     * @return 최단 거리 0을 기록한 그래프 return
     * <p>
     * 1.BFS 사용 -> 이중 for 문을 돌면서 업데이트할 예정
     * 2. 2중 for 문의 결과물로 나온 값 -> 최단 거리 이걸로 i, j를 담을 예정
     */
    private final int[][] go = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int[][] updateMatrix(int[][] mat) {
        int[][] answer = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    continue;
                }
                int distance = bfs(i, j, mat);
                answer[i][j] = distance;
            }
        }
        return answer;
    }

    private int bfs(int row, int col, int[][] mat) {
        int count = 0;
        boolean[][] isVisited = new boolean[mat.length][mat[0].length];
        isVisited[row][col] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < go.length; i++) {
                int newRow = poll[0] + go[i][0];
                int newCol = poll[1] + go[i][1];

                if (isValidate(newRow, newCol, mat) && !isVisited[newRow][newCol]) {
                    if (mat[newRow][newCol] == 0) {
                        return poll[2] + 1;
                    }
                    isVisited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol, poll[2] + 1});
                }
            }
        }

        return count;
    }

    private boolean isValidate(int newRow, int newCol, int[][] mat) {
        return newRow >= 0 && newCol >= 0 && newRow < mat.length && newCol < mat[0].length;
    }
}

