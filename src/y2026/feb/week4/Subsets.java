package y2026.feb.week4;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        subsets.subsets(new int[]{1, 2, 3});
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        boolean[] isVisited = new boolean[nums.length];
        dfs(answer, isVisited, nums, new ArrayList<>(), 0);
        return answer.stream().toList();
    }

    private void dfs(List<List<Integer>> answer, boolean[] isVisited, int[] nums, List<Integer> temp, int start) {
        answer.add(temp);

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(answer, isVisited, nums, new ArrayList<>(temp), i + 1);
            isVisited[i] = false;
            temp.removeLast();
        }
    }
}
