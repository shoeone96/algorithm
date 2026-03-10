package all.recursive;

public class Travel {
    private static int answer = -1;

    public int solution(int balance, int[][] countries) {
        boolean[] isVisited = new boolean[countries.length];
        answer = -1;
        dfs(balance, countries, 0, isVisited);
        return answer;
    }

    private void dfs(int left, int[][] countries, int count, boolean[] isVisited) {
        if (count == countries.length) {
            answer = count;
        }

        for (int i = 0; i < countries.length; i++) {
            if (!isVisited[i] && left - countries[i][1] >= 0) {
                isVisited[i] = true;
                left -= countries[i][0];
                dfs(left, countries, count + 1, isVisited);
                isVisited[i] = false;
                left += countries[i][0];
            }
        }
        
        answer = count;
    }
}
