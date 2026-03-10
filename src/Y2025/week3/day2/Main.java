package Y2025.week3.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        StringBuilder answer = new StringBuilder();
        answer.append("<");

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            queue.addLast(i);
        }

        int count = 1;
        while (!queue.isEmpty()) {  // 큐가 완전히 빌 때까지
            Integer poll = queue.poll();
            if (count == K) {
                answer.append(poll);
                if (!queue.isEmpty()) {  // 마지막이 아니면
                    answer.append(", ");
                }
                count = 1;
            } else {
                queue.addLast(poll);
                count++;
            }
        }

        answer.append(">");

        System.out.println(answer);
    }
}
