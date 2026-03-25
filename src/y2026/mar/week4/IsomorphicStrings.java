package y2026.mar.week4;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
    /**
     * two words s, t
     * -> Are they isomorphic?
     * mapping two words and check whether using same pattern
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (!map.containsKey(s.charAt(i))) {
                if (map.containsValue(t.charAt(i))) return false;
                map.put(s.charAt(i), t.charAt(i));
                continue;
            }

            if (!map.get(s.charAt(i)).equals(t.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
