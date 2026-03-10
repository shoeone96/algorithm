package y2026.feb.week4;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
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
