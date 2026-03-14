package y2026.mar.week1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class RedBlueTeam {
    public boolean solution(int[][] friends) {
        int[] color = new int[friends.length];
        Arrays.fill(color, -1);

        for (int i = 0; i < friends.length; i++) {
            if (color[i] == -1) {
                color[i] = 0;
                if (!bfs(i, friends, color)) return false;
            }
        }
        return true;
    }

    private boolean bfs(int start, int[][] friends, int[] color) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (int ele : friends[poll]) {
                if (color[ele] == color[poll]) {
                    return false;
                }

                if (color[ele] == -1) {
                    color[ele] = 1 - color[poll];
                    queue.offer(ele);
                }
            }
        }

        return true;
    }
}
