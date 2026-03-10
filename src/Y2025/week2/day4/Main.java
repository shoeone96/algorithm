package Y2025.week2.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringBuilder sb = new StringBuilder();
        char[] sentence = line.toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        while ((sentence.length != 1) && sentence[0] != '.') {
            boolean isDone = false;
            for (char ele : sentence) {
                if (ele == '(' || ele == '[') {
                    stack.push(ele);
                } else if (ele == ')') {
                    if (stack.isEmpty() || stack.peek() == '[') {
                        sb.append("no\n");
                        isDone = true;
                        break;
                    }
                    stack.pop();
                } else if (ele == ']') {
                    if (stack.isEmpty() || stack.peek() == '(') {
                        sb.append("no\n");
                        isDone = true;
                        break;
                    }
                    stack.pop();
                }
            }

            if (!isDone) {
                if (stack.isEmpty()) {
                    sb.append("yes\n");
                } else {
                    sb.append("no\n");
                }
            }

            line = br.readLine();
            sentence = line.toCharArray();
            stack = new ArrayDeque<>();
        }

        System.out.println(sb);
    }
}
