package y2026.feb.week4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> answer = new HashSet<>();
        dfs(answer, candidates, target, new ArrayList<>(), 0);
        return answer.stream().toList();
    }

    private void dfs(Set<List<Integer>> answer, int[] candidates, int target, List<Integer> temp, int sum) {
        if (sum >= target) {
            if (sum == target) {
                List<Integer> sortedTemp = List.copyOf(temp).stream().sorted().toList();
                answer.add(sortedTemp);
            }
            return;
        }

        for (int i = 0; i < candidates.length; i++) {
            if (i == 0 || candidates[i] != candidates[i - 1]) {
                temp.add(candidates[i]);
                sum += candidates[i];
                dfs(answer, candidates, target, temp, sum);
                temp.removeLast();
                sum -= candidates[i];
            }
        }
    }
}
