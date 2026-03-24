package y2026.mar.week4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Folder {
    public String solution(String[][] folders, String p, String q) {
        Map<String, String> map = new HashMap<>();
        for (String[] folder : folders) {
            String parent = folder[0];
            String child = folder[1];
            map.put(child, parent);
        }
        return tree(p, q, map);
    }

    private String tree(String start1, String start2, Map<String, String> map) {
        Set<String> set = new HashSet<>();
        set.add(start1);
        while (map.get(start1) != null) {
            String parent = map.get(start1);
            set.add(parent);
            start1 = map.get(start1);
        }
        if (set.contains(start2)) return start2;

        while (map.get(start2) != null) {
            String parent = map.get(start2);
            if (set.contains(parent)) return parent;
            start2 = map.get(start2);
        }

        return "false";
    }
}
