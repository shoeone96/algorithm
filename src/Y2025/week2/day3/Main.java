package Y2025.week2.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        ArrayDeque<Character> stack;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            stack = new ArrayDeque<>();
            boolean isBreak = false;

            String vps = st.nextToken();
            for (char ele : vps.toCharArray()) {
                if (ele == '(') {
                    stack.push(ele);
                } else {
                    if (stack.isEmpty()) {
                        sb.append("NO\n");
                        isBreak = true;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
            if (!isBreak) {
                if (stack.isEmpty()) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        System.out.println(sb);
    }
}
