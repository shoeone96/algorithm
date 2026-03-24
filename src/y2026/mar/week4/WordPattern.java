package y2026.mar.week4;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        char[] patternCharArray = pattern.toCharArray();
        String[] stringArray = s.split(" ");
        if (patternCharArray.length != stringArray.length) {
            return false;
        }

        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < stringArray.length; i++) {
            if (!map.containsKey(patternCharArray[i])) {
                if (map.containsValue(stringArray[i])) return false;
                map.put(patternCharArray[i], stringArray[i]);
                continue;
            }

            if (!map.get(patternCharArray[i]).equals(stringArray[i])) {
                return false;
            }
        }

        return true;
    }
}
