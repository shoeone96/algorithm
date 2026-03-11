package y2026.mar.week1;

public class NumberOfProvince2 {
    public int findCircleNum(int[][] isConnected) {
        int answer = 0;
        boolean[] isVisited = new boolean[isConnected.length];

        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                dfs(i, isVisited, isConnected);
                answer++;
            }
        }

        return answer;
    }

    private void dfs(int start, boolean[] isVisited, int[][] connectList) {
        isVisited[start] = true;
        for (int i = 0; i < connectList[start].length; i++) {
            if (!isVisited[i] && connectList[start][i] == 1) {
                dfs(i, isVisited, connectList);
            }
        }
    }
}
