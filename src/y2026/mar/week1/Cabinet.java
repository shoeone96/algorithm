package y2026.mar.week1;

public class Cabinet {
    public int solution(int[][] lockers) {
        int count = 0;
        boolean[] isVisited = new boolean[lockers.length];
        dfs(lockers, isVisited, 0);
        for (boolean ele : isVisited) {
            if (!ele) count++;
        }
        return count;
    }

    private void dfs(int[][] lockers, boolean[] isVisited, int start) {
        isVisited[start] = true;

        for (int ele : lockers[start]) {
            if (!isVisited[ele]) {
                dfs(lockers, isVisited, ele);
            }
        }
    }
}
