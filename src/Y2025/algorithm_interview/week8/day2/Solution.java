package Y2025.algorithm_interview.week8.day2;

import java.util.Arrays;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        Arrays.fill(answer, 1);
        int[] left = new int[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            if (i == 0) {
                left[i] = 1;
                continue;
            }

            left[i] = left[i - 1] * nums[i - 1];
        }

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1; j++) {
                if (i != j) {
                    answer[i] *= left[i];
                }
            }
        }

        return answer;
    }
}