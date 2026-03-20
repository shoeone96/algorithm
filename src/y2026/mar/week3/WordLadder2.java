package y2026.mar.week3;

import java.util.*;

public class WordLadder2 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
            }
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                String word = queue.poll();
                if (word.equals(endWord))
                    return depth;

                for (int i = 0; i < word.length(); i++) {
                    String key = word.substring(0, i) + "*" + word.substring(i + 1);
                    for (String neighbor : map.getOrDefault(key, new ArrayList<>())) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.offer(neighbor);
                        }
                    }
                }
            }
            depth++;
        }
        return 0;
    }
}
