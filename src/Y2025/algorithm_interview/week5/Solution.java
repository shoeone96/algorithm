package Y2025.algorithm_interview.week5;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    int[] answer = new int[2];
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                }
            }
        }

        return new int[2];
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        int[] ints = {0, 4, 3, 0};
        System.out.println("ints = " + solution2.twoSum(ints, 0));
        ;
    }
}

class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numsMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (numsMap.containsKey(target - nums[i]) && i != numsMap.get(target - nums[i])) {
                return new int[]{i, numsMap.get(target - nums[i])};
            }
        }

        return null;
    }
}

