package Y2025.week3.day1;

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
            queue.addLast(i);
        }

        boolean isTrash = true;
        while (queue.size() != 1) {
            if (isTrash) {
                queue.pollFirst();
            } else {
                Integer notTrashed = queue.pollFirst();
                queue.addLast(notTrashed);
            }

            isTrash = !isTrash;
        }

        System.out.println(queue.pop());
    }
}
