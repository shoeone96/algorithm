package y2026.feb.week4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations2_2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> answer = new HashSet<>();
        boolean[] isVisited = new boolean[nums.length];
        dfs(nums, answer, new ArrayList<>(), isVisited);
        return answer.stream().toList();
    }

    private void dfs(int[] nums, Set<List<Integer>> answer, ArrayList<Integer> temp, boolean[] isVisited) {
        if (temp.size() == nums.length) {
            answer.add(List.copyOf(temp));
        }

        for (int i = 0; i < nums.length; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                temp.add(nums[i]);
                dfs(nums, answer, temp, isVisited);
                temp.removeLast();
                isVisited[i] = false;
            }
        }
    }
}
