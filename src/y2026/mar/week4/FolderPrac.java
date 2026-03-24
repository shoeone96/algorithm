package y2026.mar.week4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FolderPrac {
    public String solution(String[][] folders, String p, String q) {
        Map<String, String> tree = new HashMap<>();
        for (String[] folder : folders) {
            String parent = folder[0];
            String child = folder[1];
            tree.put(child, parent);
        }

        Set<String> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = tree.get(p);
        }

        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            set.add(q);
            q = tree.get(q);
        }
        return null;
    }
}
