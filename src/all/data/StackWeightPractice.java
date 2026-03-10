package all.data;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackWeightPractice {
    public int[] solution(int[] weights) {
        int n = weights.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && weights[i] > weights[stack.peek()]) {
                Integer pop = stack.pop();
                answer[pop] = i - pop;
            }

            stack.push(i);
        }
        return answer;
    }
}
