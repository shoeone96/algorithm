package Y2025.week4.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static final String PUSH = "push";
    private static final String POP = "pop";
    private static final String SIZE = "size";
    private static final String EMPTY = "empty";
    private static final String FRONT = "front";
    private static final String BACK = "back";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        StringBuilder answer = new StringBuilder();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String ele = st.nextToken();

            if (ele.startsWith(PUSH)) {
                int push = Integer.parseInt(st.nextToken());
                queue.addLast(push);
            } else if (ele.startsWith(POP)) {
                if (queue.isEmpty()) {
                    answer.append("-1\n");
                } else {
                    answer.append(queue.pollFirst()).append("\n");
                }
            } else if (ele.startsWith(SIZE)) {
                answer.append(queue.size()).append("\n");
            } else if (ele.startsWith(EMPTY)) {
                if (queue.isEmpty()) {
                    answer.append("1\n");
                } else {
                    answer.append("0\n");
                }
            } else if (ele.startsWith(FRONT)) {
                if (queue.isEmpty()) {
                    answer.append("-1\n");
                } else {
                    answer.append(queue.peekFirst()).append("\n");
                }
            } else {
                if (queue.isEmpty()) {
                    answer.append("-1\n");
                } else {
                    answer.append(queue.peekLast()).append("\n");
                }
            }
        }
        System.out.println(answer);

    }
}
