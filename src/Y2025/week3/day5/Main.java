package Y2025.week3.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            queue.addFirst(i);
        }

        while (queue.size() > 1) {
            queue.pollLast();
            if (queue.size() == 1) {
                break;
            } else {
                queue.addFirst(queue.pollLast());
                ;
            }
        }

        System.out.println(queue.pop());
    }
}
