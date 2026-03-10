package all.data;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackWeight2 {
    public int[] solution(int[] weights) {
        int n = weights.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && weights[i] > weights[stack.peek()]) {
                int prev = stack.pop();
                answer[prev] = i - prev;
            }
            stack.push(i);
        }
        return answer;
    }
}
