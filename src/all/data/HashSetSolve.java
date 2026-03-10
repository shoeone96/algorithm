package all.data;

import java.util.HashSet;

public class HashSetSolve {
    public int solution(int[] prices, int target) {
        int answer = 0;
        HashSet<Integer> pricesSet = new HashSet<>();
        for (int ele : prices) {
            pricesSet.add(ele);
        }
        for (int ele : prices) {
            if (pricesSet.contains(target - ele)) {
                answer++;
            }
        }
        return answer / 2;
    }
}
