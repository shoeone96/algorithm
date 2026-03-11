package y2026.mar.week1;

import java.util.List;

public class KeysAndRooms2 {
    private int count = 0;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean answer = true;
        boolean[] isVisited = new boolean[rooms.size()];
        dfs(0, rooms, isVisited);
        if (count < rooms.size()) {
            return false;
        }
        return answer;
    }

    private void dfs(int start, List<List<Integer>> rooms, boolean[] isVisited) {
        isVisited[start] = true;
        count++;
        for (int next : rooms.get(start)) {
            if (!isVisited[next]) {
                dfs(next, rooms, isVisited);
            }
        }
    }
}
