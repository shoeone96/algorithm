package y2026.mar.week4;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WordPattern3 {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }

        Map<Object, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!Objects.equals(map.put(pattern.charAt(i), i), map.put(words[i], i))) {
                return false;
            }
        }

        return true;
    }
}
