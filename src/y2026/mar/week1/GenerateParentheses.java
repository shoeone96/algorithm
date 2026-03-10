package y2026.mar.week1;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();
        backTracking(answer, n - 1, n, new StringBuilder().append("("));
        return answer;
    }

    void backTracking(List<String> answer, int leftCount, int rightCount, StringBuilder temp) {
        if (leftCount == 0 && rightCount == 0) {
            answer.add(temp.toString());
            return;
        }

        for (int i = 0; i < 2; i++) {
            if (i == 0 && leftCount != 0) {
                temp.append("(");
                backTracking(answer, leftCount - 1, rightCount, temp);
                temp.deleteCharAt(temp.length() - 1);
                continue;
            }

            if (i == 1 && rightCount != 0 && rightCount > leftCount) {
                temp.append(")");
                backTracking(answer, leftCount, rightCount - 1, temp);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }
}
