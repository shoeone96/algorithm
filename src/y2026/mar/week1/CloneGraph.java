package y2026.mar.week1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {

    public Node cloneGraph(Node node) {
        Map<Node, Node> nodeMap = new HashMap<>();
        dfs(nodeMap, node);
        return nodeMap.get(node);
    }

    private void dfs(Map<Node, Node> nodeMap, Node now) {
        if (now == null) {
            return;
        }
        Node copyNode = new Node(now.val, new ArrayList<>());
        nodeMap.put(now, copyNode);
        for (Node ele : now.neighbors) {
            if (!nodeMap.containsKey(ele)) {
                dfs(nodeMap, ele);
            }

            copyNode.neighbors.add(nodeMap.get(ele));
        }
    }

}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}