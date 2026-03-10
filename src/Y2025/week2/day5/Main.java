package Y2025.week2.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        int n = Integer.parseInt(st.nextToken());
        String next;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            next = st.nextToken();

            if (next.equals("push")) {
                queue.offer(Integer.parseInt(st.nextToken()));
            } else if (next.equals("front")) {
                if (queue.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(queue.getFirst()).append("\n");
                }
            } else if (next.equals("back")) {
                if (queue.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(queue.getLast()).append("\n");
                }
            } else if (next.equals("size")) {
                sb.append(queue.size()).append("\n");
            } else if (next.equals("pop")) {
                if (queue.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(queue.pollFirst()).append("\n");
                }
            } else if (next.equals("empty")) {
                if (queue.isEmpty()) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            }
        }
        System.out.println(sb);
    }
}
