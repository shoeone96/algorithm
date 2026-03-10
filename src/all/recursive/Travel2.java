package all.recursive;

public class Travel2 {
    private static int answer = -1;

    public int solution(int balance, int[][] countries) {
        boolean[] isVisited = new boolean[countries.length];
        answer = -1;
        dfs(balance, countries, 0, isVisited);
        return answer;
    }

    private void dfs(int left, int[][] countries, int count, boolean[] isVisited) {
        answer = Math.max(answer, count);

        for (int i = 0; i < countries.length; i++) {
            if (!isVisited[i] && left >= countries[i][1]) {
                isVisited[i] = true;
                dfs(left - countries[i][0], countries, count + 1, isVisited);
                isVisited[i] = false;
            }
        }
    }
}
