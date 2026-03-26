package y2026.mar.week4;

import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = String.valueOf(chars);
            if (map.containsKey(sortedStr)) {
                Integer index = map.get(sortedStr);
                answer.get(index).add(str);
            } else {
                answer.add(new ArrayList<>());
                answer.getLast().add(str);
                map.put(sortedStr, answer.size() - 1);
            }
        }

        return answer;
    }
}
