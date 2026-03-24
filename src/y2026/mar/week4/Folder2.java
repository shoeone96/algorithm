package y2026.mar.week4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Folder2 {
    public String solution(String[][] folders, String p, String q) {
        Map<String, List<String>> tree = new HashMap<>();
        for (String[] ele : folders) {
            String parent = ele[0];
            String child = ele[1];
            tree.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
        }

        return dfs(tree, "root", p, q);
    }

    private String dfs(Map<String, List<String>> tree, String node, String p, String q) {
        if (node.equals(p) || node.equals(q)) {
            return node;
        }
        List<String> found = new ArrayList<>();
        List<String> children = tree.getOrDefault(node, new ArrayList<>());
        for (String child : children) {
            String res = dfs(tree, child, p, q);
            if (res != null) {
                found.add(res);
            }
        }
        if (found.size() == 1) return found.get(0);
        if (found.size() == 2) return node;
        return null;
    }
}
