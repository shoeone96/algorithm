package y2026.feb.week4;

import java.util.ArrayList;
import java.util.List;

public class Subsets2 {
    public static void main(String[] args) {
        new Subsets2().subsetsWithDup(new int[]{4, 4, 4, 1, 4});
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        dfs(answer, nums, 0, new ArrayList<>());
        return answer;
    }

    private void dfs(List<List<Integer>> answer, int[] nums, int startIndex, List<Integer> temp) {
        List<Integer> list = List.copyOf(temp).stream().sorted().toList();
        if (!answer.contains(list)) {
            answer.add(list);
        }

        for (int i = startIndex; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(answer, nums, i + 1, temp);
            temp.removeLast();
        }
    }
}
