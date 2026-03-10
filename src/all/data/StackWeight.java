package all.data;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackWeight {
    public int[] solution(int[] weights) {
        int n = weights.length;
        int[] answer = new int[n];
        Deque<int[]> deque = new ArrayDeque<>();

        for (int curDate = 0; curDate < n; curDate++) {
            int curW = weights[curDate];

            while (!deque.isEmpty() && deque.peek()[1] < curW) {
                int[] item = deque.pop();
                int date = item[0];
                answer[date] = curDate - date;
            }

            deque.push(new int[]{curDate, curW});
        }
        return answer;
    }
}
