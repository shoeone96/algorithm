package y2026.feb.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations2_1 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] isSkip = new boolean[nums.length];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                isSkip[i] = true;
            }
        }
        List<List<Integer>> answer = new ArrayList<>();
        boolean[] isVisited = new boolean[nums.length];
        dfs(nums, answer, new ArrayList<>(), isVisited, isSkip);
        return answer;
    }

    private void dfs(int[] nums, List<List<Integer>> answer, ArrayList<Integer> temp, boolean[] isVisited, boolean[] isSkip) {
        if (temp.size() == nums.length) {
            answer.add(List.copyOf(temp));
        }

        for (int i = 0; i < nums.length; i++) {
            if (temp.isEmpty() && isSkip[i]) continue;
            if (!isVisited[i]) {
                isVisited[i] = true;
                temp.add(nums[i]);
                dfs(nums, answer, temp, isVisited, isSkip);
                temp.removeLast();
                isVisited[i] = false;
            }
        }
    }
}
