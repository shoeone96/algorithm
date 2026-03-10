package all.basic;

public class TimeComplexity1 {
    public static void main(String[] args) {
        solution(1000);
    }

    public static int solution(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                count++;
            }
        }

        return count;
    }
}
