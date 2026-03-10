package all.data;

import java.util.Arrays;

public class Array {
    public static void main(String[] args) {
        new Array().solution(new int[]{4, 1, 9, 7, 5, 3, 16}, 14);
    }

    public int solution(int[] prices, int target) {
        int answer = 0;
        Arrays.sort(prices);
        int a = 0;
        int b = prices.length - 1;

        while (prices[a] != prices[b]) {
            if (prices[a] + prices[b] > target) {
                b--;
            } else if (prices[a] + prices[b] < target) {
                a++;
            } else {
                answer++;
                a++;
            }
        }
        return answer;
    }
}
