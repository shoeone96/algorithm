package y2026.feb.week4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subsets2_1 {
    public static void main(String[] args) {
        new Subsets2_1().subsetsWithDup(new int[]{4, 4, 4, 1, 4});
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> answer = new HashSet<>();
        dfs(answer, nums, 0, new ArrayList<>());
        return answer.stream().toList();
    }

    private void dfs(Set<List<Integer>> answer, int[] nums, int startIndex, List<Integer> temp) {
        List<Integer> list = List.copyOf(temp).stream().sorted().toList();
        answer.add(list);


        for (int i = startIndex; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(answer, nums, i + 1, temp);
            temp.removeLast();
        }
    }
}
