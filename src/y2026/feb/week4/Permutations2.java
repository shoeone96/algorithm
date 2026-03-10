package y2026.feb.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();
        boolean[] isVisited = new boolean[nums.length];
        dfs(nums, answer, new ArrayList<>(), isVisited);
        return answer;
    }

    private void dfs(int[] nums, List<List<Integer>> answer, ArrayList<Integer> temp, boolean[] isVisited) {
        if (temp.size() == nums.length) {
            answer.add(List.copyOf(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (isVisited[i] || (i > 0 && nums[i] == nums[i - 1] && !isVisited[i - 1])) {
                continue;
            }
            isVisited[i] = true;
            temp.add(nums[i]);
            dfs(nums, answer, temp, isVisited);
            temp.removeLast();
            isVisited[i] = false;
        }
    }
}
