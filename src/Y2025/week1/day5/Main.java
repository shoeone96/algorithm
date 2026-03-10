package Y2025.week1.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String word = st.nextToken();

        String lowerCasedWord = word.toLowerCase();
        int value = 0;
        Map<Character, Integer> answer = new HashMap<>();
        Map<Character, Integer> countMap = new HashMap<>();
        for (char ch : lowerCasedWord.toCharArray()) {
            Integer getOrDefault = countMap.getOrDefault(ch, 0);
            countMap.put(ch, getOrDefault + 1);
        }

        for (char key : countMap.keySet()) {
            if (countMap.get(key) > value) {
                answer = new HashMap<>();
                answer.put(key, countMap.get(key));
                value = countMap.get(key);
                continue;
            }

            if (countMap.get(key) == value) {
                answer = new HashMap<>();
                answer.put('?', countMap.get(key));
            }
        }
        Character realAnswer = (Character) answer.keySet().toArray()[0];
        char upperCase = Character.toUpperCase(realAnswer);
        System.out.println(upperCase);
    }
}
