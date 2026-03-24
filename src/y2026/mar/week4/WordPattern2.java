package y2026.mar.week4;

import java.util.HashMap;
import java.util.Map;

public class WordPattern2 {
    public boolean wordPattern(String pattern, String s) {
        char[] patternCharArray = pattern.toCharArray();
        String[] stringArray = s.split(" ");
        if (patternCharArray.length != stringArray.length) {
            return false;
        }

        Map<Character, String> map = new HashMap<>();
        Map<String, Character> stringMap = new HashMap<>();
        for (int i = 0; i < stringArray.length; i++) {
            if (!map.containsKey(patternCharArray[i]) && !stringMap.containsKey(stringArray[i])) {
                stringMap.put(stringArray[i], patternCharArray[i]);
                map.put(patternCharArray[i], stringArray[i]);
                continue;
            }
            if (map.containsKey(patternCharArray[i]) != stringMap.containsKey(stringArray[i])) {
                return false;
            }
            if (!map.get(patternCharArray[i]).equals(stringArray[i])
                    || !stringMap.get(stringArray[i]).equals(patternCharArray[i])) {
                return false;
            }
        }

        return true;
    }
}
