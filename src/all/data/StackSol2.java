package all.data;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackSol2 {
    public int solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int answer = 0;
        for (char ele : s.toCharArray()) {
            if (ele == '(' || ele == '{' || ele == '[') {
                stack.add(ele);
            } else if (ele == ')') {
                if (stack.isEmpty() || stack.peekLast() != '(') {
                    return -1;
                } else {
                    stack.pollLast();
                    answer++;
                }
            } else if (ele == '}') {
                if (stack.isEmpty() || stack.peekLast() != '{') {
                    return -1;
                } else {
                    stack.pollLast();
                    answer++;
                }
            } else if (ele == ']') {
                if (stack.isEmpty() || stack.peekLast() != '[') {
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
