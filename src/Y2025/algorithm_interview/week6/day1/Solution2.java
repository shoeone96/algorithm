package Y2025.algorithm_interview.week6.day1;

public class Solution2 {
    public int trap(int[] height) {
        int answer = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];

        while (left < right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);

            if (leftMax <= rightMax) {
                answer += leftMax - height[left];
                left += 1;
            } else {
                answer += rightMax - height[right];
                right -= 1;
            }

        }
        return answer;
    }

}
