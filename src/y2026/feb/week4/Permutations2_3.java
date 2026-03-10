package y2026.feb.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations2_3 {
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
            if (i == 0 && !isVisited[i]) {
                isVisited[i] = true;
                temp.add(nums[i]);
                dfs(nums, answer, temp, isVisited);
                temp.removeLast();
                isVisited[i] = false;
                continue;
            }
            if (i > 0 && !isVisited[i] && !(nums[i] == nums[i - 1] && isVisited[i - 1])) {
                isVisited[i] = true;
                temp.add(nums[i]);
                dfs(nums, answer, temp, isVisited);
                temp.removeLast();
                isVisited[i] = false;
            }
        }
    }
}
