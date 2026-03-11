package y2026.mar.week1;

public class FloodFill {
    private static final int[][] go = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int st;

    public static void main(String[] args) {

        int[][] arr = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        new FloodFill().floodFill(arr, 1, 1, 2);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        st = image[sr][sc];
        if (st != color) {
            dfs(sr, sc, image, color);
        }
        return image;
    }

    private void dfs(int row, int col, int[][] image, int color) {
        image[row][col] = color;

        for (int i = 0; i < go.length; i++) {
            int newRow = row + go[i][0];
            int newCol = col + go[i][1];
            if (isValidated(newRow, newCol, image.length, image[0].length) && image[newRow][newCol] == st) {
                dfs(newRow, newCol, image, color);
            }
        }
    }

    private boolean isValidated(int newRow, int newCol, int stRow, int stCol) {
        if (newRow < 0 || newCol < 0 || newRow >= stRow || newCol >= stCol) {
            return false;
        }
        return true;
    }
}
