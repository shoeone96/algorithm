package y2026.mar.week1;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        backTracking(candidates, target, new ArrayList<>(), 0, answer);
        return answer;
    }

    private void backTracking(int[] candidates, int target, List<Integer> temp, int start, List<List<Integer>> answer) {
        int tempSum = 0;
        for (int ele : temp) {
            tempSum += ele;
        }
        if (tempSum == target) {
            answer.add(List.copyOf(temp));
            return;
        } else if (tempSum > target) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            backTracking(candidates, target, temp, i, answer);
            temp.removeLast();
        }
    }
}
