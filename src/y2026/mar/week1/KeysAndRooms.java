package y2026.mar.week1;

import java.util.List;

public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean answer = true;
        boolean[] isVisited = new boolean[rooms.size()];
        dfs(0, rooms, isVisited);
        for (boolean ele : isVisited) {
            if (!ele) {
                answer = false;
                break;
            }
        }
        return answer;
    }

    private void dfs(int start, List<List<Integer>> rooms, boolean[] isVisited) {
        isVisited[start] = true;
        for (int next : rooms.get(start)) {
            if (!isVisited[next]) {
                dfs(next, rooms, isVisited);
            }
        }
    }
}
