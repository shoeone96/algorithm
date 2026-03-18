package y2026.mar.week3;

import java.util.ArrayDeque;
import java.util.Queue;

public class SurroundedRegions2 {

    private final int[][] go = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
                    if (board[i][j] == 'O') {
                        bfs(i, j, board);
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void bfs(int row, int col, char[][] board) {
        Queue<int[]> queue = new ArrayDeque<>();
        board[row][col] = 'T';
        queue.offer(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int i = 0; i < go.length; i++) {
                int newRow = poll[0] + go[i][0];
                int newCol = poll[1] + go[i][1];

                if (isValidated(newRow, newCol, board) && board[newRow][newCol] == 'O') {
                    queue.offer(new int[]{newRow, newCol});
                    board[newRow][newCol] = 'T';
                }
            }
        }
    }

    private boolean isValidated(int newRow, int newCol, char[][] board) {
        return newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length;
    }
}
