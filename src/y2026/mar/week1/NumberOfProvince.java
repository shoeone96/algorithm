package y2026.mar.week1;

import java.util.ArrayList;
import java.util.List;

public class NumberOfProvince {
    public int findCircleNum(int[][] isConnected) {
        int answer = 0;
        boolean[] isVisited = new boolean[isConnected.length];
        List<List<Integer>> connectList = new ArrayList<>();
        for (int i = 0; i < isConnected.length; i++) {
            connectList.add(new ArrayList<>());
        }

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected[0].length; j++) {
                if (isConnected[i][j] == 1) {
                    connectList.get(i).add(j);
                    connectList.get(j).add(i);
                }
            }
        }

        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                dfs(i, isVisited, connectList);
                answer++;
            }
        }

        return answer;
    }

    private void dfs(int start, boolean[] isVisited, List<List<Integer>> connectList) {
        isVisited[start] = true;

        for (int next : connectList.get(start)) {
            if (!isVisited[next]) {
                dfs(next, isVisited, connectList);
            }
        }
    }
}
