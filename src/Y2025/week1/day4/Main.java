package Y2025.week1.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int maxValue = Integer.parseInt(st.nextToken());
        int location = 1;

        for (int i = 0; i < 8; i++) {
            int newNumber = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
            if (newNumber > maxValue) {
                maxValue = newNumber;
                location = i + 2;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxValue).append("\n").append(location);

        System.out.println(sb);
    }
}
