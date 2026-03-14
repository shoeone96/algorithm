package y2026.mar.week1;

import java.util.Arrays;

public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int[] group = new int[graph.length];
        Arrays.fill(group, -1);

        for (int i = 0; i < graph.length; i++) {
            if (group[i] == -1) {
                group[i] = 0;
            }
            if (!dfs(i, graph, group)) return false;
        }

        return true;
    }

    private boolean dfs(int start, int[][] graph, int[] group) {
        for (int ele : graph[start]) {
            if (group[ele] == group[start]) {
                return false;
            }

            if (group[ele] == -1) {
                group[ele] = 1 - group[start];
                if (!dfs(ele, graph, group)) return false;
            }
        }
        return true;
    }
}
