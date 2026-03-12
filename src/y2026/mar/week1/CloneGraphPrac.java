package y2026.mar.week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CloneGraphPrac {

    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        dfs(map, node);
        return map.get(node);
    }

    private void dfs(Map<Node, Node> map, Node node) {
        if (node == null) {
            return;
        }
        Node copyNode = new Node(node.val, new ArrayList<>());
        map.put(node, copyNode);

        for (Node ele : node.neighbors) {
            if (!map.containsKey(ele)) {
                dfs(map, ele);
            }

            copyNode.neighbors.add(map.get(ele));
        }
    }

}
