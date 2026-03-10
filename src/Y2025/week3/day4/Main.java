package Y2025.week3.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static String PUSH_FRONT = "push_front";
    public static String PUSH_BACK = "push_back";
    public static String POP_FRONT = "pop_front";
    public static String POP_BACK = "pop_back";
    public static String SIZE = "size";
    public static String EMPTY = "empty";
    public static String FRONT = "front";
    public static String BACK = "back";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            String newCommand = br.readLine();

            if (newCommand.startsWith(PUSH_FRONT)) {
                String[] split = newCommand.split(" ");
                deque.addFirst(Integer.parseInt(split[1]));
                continue;
            }

            if (newCommand.startsWith(PUSH_BACK)) {
                String[] split = newCommand.split(" ");
                deque.addLast(Integer.parseInt(split[1]));
                continue;
            }

            if (newCommand.startsWith(POP_FRONT)) {
                if (deque.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(deque.pollFirst()).append("\n");
                }
                continue;
            }

            if (newCommand.startsWith(POP_BACK)) {
                if (deque.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(deque.pollLast()).append("\n");
                }
                continue;
            }

            if (newCommand.startsWith(SIZE)) {
                sb.append(deque.size()).append("\n");
                continue;
            }

            if (newCommand.startsWith(EMPTY)) {
                if (deque.isEmpty()) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
                continue;
            }

            if (newCommand.startsWith(FRONT)) {
                if (deque.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(deque.peekFirst()).append("\n");
                }
                continue;
            }

            if (newCommand.startsWith(BACK)) {
                if (deque.isEmpty()) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(deque.peekLast()).append("\n");
                }
            }
        }

        System.out.println(sb);
    }
}
