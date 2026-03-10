package Y2025.algorithm_interview.week7.day3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> answer = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int temp = nums[i] + nums[left] + nums[right];
                if (temp < 0) {
                    left++;
                } else if (temp > 0) {
                    right--;
                } else {
                    List<Integer> list = List.of(nums[i], nums[left], nums[right]);
                    answer.add(list);
                    left++;
                }
            }
        }
        return answer.stream().toList();
    }

}
