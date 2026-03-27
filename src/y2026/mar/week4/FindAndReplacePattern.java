package y2026.mar.week4;

import java.util.*;

public class FindAndReplacePattern {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> answer = new ArrayList<>();
        for (String word : words) {
            if (patternMatching(pattern, word)) {
                answer.add(word);
            }
        }
        return answer;
    }

    private boolean patternMatching(String pattern, String word) {
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        int length = pattern.length();
        for (int i = 0; i < length; i++) {
            if (!map.containsKey(pattern.charAt(i))) {
                if (set.contains(word.charAt(i))) {
                    return false;
                }

                map.put(pattern.charAt(i), word.charAt(i));
                set.add(word.charAt(i));
            } else {
                Character value = map.get(pattern.charAt(i));
                if (!value.equals(word.charAt(i))) return false;
            }
        }

        return true;
    }
}
