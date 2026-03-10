package y2026.feb.week4;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        combinations.combine(4, 2);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        dfs(n, k, new ArrayList<>(), 1, answer);
        return answer;
    }

    private void dfs(int n, int k, List<Integer> temp, int index, List<List<Integer>> answer) {
        if (temp.size() == k) {
            answer.add(List.copyOf(temp));
            return;
        }

        for (int i = index; i <= n; i++) {
            temp.add(i);
            dfs(n, k, temp, i + 1, answer);
            temp.removeLast();
        }
    }
}
