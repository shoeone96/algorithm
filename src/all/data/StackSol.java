package all.data;

import java.util.ArrayDeque;

public class StackSol {
    public int solution(String s) {
        int answer = 0;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char ele : s.toCharArray()) {
            if (ele == '(') {
                stack.add(ele);
            } else if (ele == ')') {
                if (stack.isEmpty()) {
                    return -1;
                } else {
                    stack.pollLast();
                    answer++;
                }
            }
        }
        if (stack.isEmpty()) {
            return answer;
        } else return -1;
    }
}
