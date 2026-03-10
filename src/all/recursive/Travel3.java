package all.recursive;

public class Travel3 {
    int answer;

    public int solution(int balance, int[][] countries) {
        answer = -1;
        boolean[] isVisited = new boolean[countries.length];
        dfs(balance, countries, 0, isVisited);
        return answer;
    }

    private void dfs(int balance, int[][] countries, int count, boolean[] isVisited) {
        for (int i = 0; i < countries.length; i++) {
            if (balance >= countries[i][1] && !isVisited[i]) {
                isVisited[i] = true;
                dfs(balance - countries[i][0], countries, count + 1, isVisited);
                isVisited[i] = false;
            }
        }

        if (count > answer) {
            answer = count;
        }
    }
}
