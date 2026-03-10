package y2026.feb.week4;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        dfs(answer, candidates, target, new ArrayList<>(), 0, 0);
        return answer;
    }

    private void dfs(List<List<Integer>> answer, int[] candidates, int target, List<Integer> temp, int sum, int startIndex) {
        if (sum >= target) {
            if (sum == target) {
                answer.add(List.copyOf(temp));
            }
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            temp.add(candidates[i]);
            sum += candidates[i];
            dfs(answer, candidates, target, temp, sum, i);
            temp.removeLast();
            sum -= candidates[i];
        }
    }
}
