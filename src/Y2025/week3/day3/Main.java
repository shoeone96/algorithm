package Y2025.week3.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());
            int count = 0;

            st = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> rank = new PriorityQueue<>(Collections.reverseOrder());
            ArrayDeque<Document> printer = new ArrayDeque<>();

            for (int j = 0; j < length; j++) {
                int ele = Integer.parseInt(st.nextToken());
                rank.add(ele);
                printer.addLast(new Document(j, ele));
            }

            while (true) {
                Document document = printer.pollFirst();
                if (document.priority == rank.peek()) {
                    count++;
                    rank.poll();

                    if (document.index == index) {
                        break;
                    }
                } else {
                    printer.addLast(document);
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }

    static class Document {
        int index;
        int priority;

        public Document(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}
