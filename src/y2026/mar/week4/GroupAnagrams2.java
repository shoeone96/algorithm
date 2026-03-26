package y2026.mar.week4;

import java.util.*;

public class GroupAnagrams2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = String.valueOf(chars);
            map.computeIfAbsent(sortedStr, m -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
