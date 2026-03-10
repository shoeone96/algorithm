package y2026.mar.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum3 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> answer = new ArrayList<>();
        backTracking(candidates, target, new ArrayList<>(), 0, answer, 0);
        return answer;
    }

    private void backTracking(int[] candidates, int target, List<Integer> temp, int start, List<List<Integer>> answer, int tempSum) {
        if (tempSum == target) {
            answer.add(List.copyOf(temp));
            return;
        } else if (tempSum > target) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (tempSum + candidates[i] > target) {
                break;
            }
            temp.add(candidates[i]);
            backTracking(candidates, target, temp, i, answer, tempSum + candidates[i]);
            temp.removeLast();
        }
    }
}
