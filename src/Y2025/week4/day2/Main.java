package Y2025.week4.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    /**
     * 함수 뒤집기, 버리기
     * 뒤집기: 배열 순서 뒤집기
     * 버리기: 첫 번째 수 버리기 (비어있지 않을때만)
     * 함수 조합 가능
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        String command;
        int size;
        String arrString;

        for (int i = 0; i < N; i++) {
            boolean isReversed = false;
            command = br.readLine();
            size = Integer.parseInt(br.readLine());
            arrString = br.readLine();
            String[] split = arrString.substring(1, arrString.length() - 1).split(",");
            ArrayDeque<Integer> arr = new ArrayDeque<>();
            if (size > 0) {
                for (String ele : split) {
                    arr.addLast(Integer.parseInt(ele));
                }
            }
            StringBuilder temp = new StringBuilder();
            for (char ele : command.toCharArray()) {
                if (ele == 'R') {
                    isReversed = !isReversed;
                } else {
                    if (arr.isEmpty()) {
                        answer.append("error\n");
                        temp.append("fail");
                        break;
                    }
                    if (isReversed) {
                        arr.pollLast();
                    } else {
                        arr.pollFirst();
                    }
                }
            }
            if (temp.length() != 0) {
                continue;
            }
            answer.append("[");
            int count = arr.size();
            for (int j = 0; j < count; j++) {
                if (isReversed) {
                    answer.append(arr.pollLast());
                } else {
                    answer.append(arr.pollFirst());
                }
                if (j != count - 1) {
                    answer.append(",");
                }
            }
            answer.append("]\n");
        }

        System.out.println(answer);
    }
}
