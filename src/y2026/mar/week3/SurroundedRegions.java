package y2026.mar.week3;

import java.util.ArrayDeque;
import java.util.Queue;

public class SurroundedRegions {

    private final int[][] go = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public void solve(char[][] board) {
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !isVisited[i][j]) {
                    if (!bfs(i, j, board, isVisited)) {
                        update(i, j, board);
                    }
                }
            }
        }
    }

    private boolean bfs(int row, int col, char[][] board, boolean[][] isVisited) {
        boolean flag = false;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        isVisited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (poll[0] == 0 || poll[0] == board.length - 1 || poll[1] == 0 || poll[1] == board[0].length - 1) {
                flag = true;
            }
            for (int i = 0; i < go.length; i++) {
                int newRow = poll[0] + go[i][0];
                int newCol = poll[1] + go[i][1];
                if (isValidated(newRow, newCol, board) && !isVisited[newRow][newCol] && board[newRow][newCol] == 'O') {
                    isVisited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return flag;
    }

    private boolean isValidated(int newRow, int newCol, char[][] board) {
        return newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length;
    }

    private void update(int row, int col, char[][] board) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, col});
        board[row][col] = 'X';

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < go.length; i++) {
                int newRow = poll[0] + go[i][0];
                int newCol = poll[1] + go[i][1];
                if (isValidated(newRow, newCol, board) && board[newRow][newCol] == 'O') {
                    board[newRow][newCol] = 'X';
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }
}
