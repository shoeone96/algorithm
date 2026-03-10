package Y2025.algorithm_interview.week6.day1;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int answer = 0;
        for (int i = 0; i < height.length; i++) {
            // 오른 쪽에 큰 게 나타났을 때
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                Integer pop = stack.pop();

                // 만약 왼쪽 벽이 없다면
                if (stack.isEmpty()) {
                    break;
                }

                int row = i - stack.peek() - 1;
                int col = Math.min(height[i], height[stack.peek()]) - height[pop];
                answer += row * col;
            }

            stack.push(i);
        }

        return answer;
    }

}
