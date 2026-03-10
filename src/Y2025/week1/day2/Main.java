package Y2025.week1.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int n = Integer.parseInt(st.nextToken());
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        printArr(arr, stack);
    }

    private static void printArr(String[] arr, ArrayDeque<Integer> stack) {
        for (String s : arr) {
            if (s.startsWith("push")) {
                String[] push = s.split(" ");
                stack.push(Integer.parseInt(push[1]));
                continue;
            }

            if (s.equals("pop")) {
                if (stack.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(stack.pop());
                }
                continue;
            }

            if (s.equals("size")) {
                System.out.println(stack.size());
            }

            if (s.equals("empty")) {
                if (stack.isEmpty()) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
                continue;
            }

            if (s.equals("top")) {
                if (stack.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(stack.peek());
                }
            }
        }
    }
}
