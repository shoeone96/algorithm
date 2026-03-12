# Clone Graph 풀이 회고 - DFS + HashMap

> **문제**: Clone Graph (LeetCode 133)
> **유형**: DFS (그래프 복제)
> **코드**: `CloneGraph.java`

---

## 1. 핵심 아이디어

`Map<Node, Node>` 하나로 두 가지 역할을 동시에 수행:
- **visited 체크**: `containsKey(node)` → 이미 방문한 노드인지 확인
- **원본-복제 매핑**: key = 원본 노드, value = 복제 노드

## 2. DFS 흐름

```
dfs(map, now):
  1. now의 복제본 생성 → map에 저장
  2. now.neighbors를 순회:
     - 미방문이면 → dfs 재귀 호출
     - (항상) 복제본의 neighbors에 map.get(ele) 추가
```

**포인트**: dfs 호출과 neighbors 연결을 분리
- `dfs` 호출은 `if (!map.containsKey(ele))` 안에서만
- `neighbors.add`는 if 밖에서 항상 실행 (이미 방문한 이웃도 연결해야 하므로)

## 3. nodeMap.get(ele)가 항상 존재하는 이유

`containsKey` 체크 안에서 `dfs`를 **먼저** 호출하기 때문에, 그 다음 줄의 `nodeMap.get(ele)`는 항상 값이 존재함이 보장된다.
- 미방문 → dfs 호출 → map에 등록됨 → get 가능
- 이미 방문 → 이미 map에 있음 → get 가능

## 4. 최종 코드 구조

```java
public Node cloneGraph(Node node) {
    if (node == null) return null;
    Map<Node, Node> nodeMap = new HashMap<>();
    dfs(nodeMap, node);
    return nodeMap.get(node);
}

private void dfs(Map<Node, Node> nodeMap, Node now) {
    Node copyNode = new Node(now.val, new ArrayList<>());
    nodeMap.put(now, copyNode);
    for (Node ele : now.neighbors) {
        if (!nodeMap.containsKey(ele)) {
            dfs(nodeMap, ele);
        }
        copyNode.neighbors.add(nodeMap.get(ele));
    }
}
```

## 5. 삽질 과정에서 배운 것

- **처음 접근**: 구조 수집(DFS) → 재구축(DFS) 두 단계로 분리 → 불필요하게 복잡
- **개선**: DFS 한 번에 방문 + 복제 + 연결을 동시에 처리
- **교훈**: Map 하나로 visited + 저장을 동시에 할 수 있는지 먼저 생각할 것

## 6. 메타인지

Node/LinkedList 같은 참조 기반 자료구조 조작이 아직 익숙하지 않음.
배열/인접행렬 기반과 달리 노드 객체를 직접 생성·연결하는 감각이 부족 → 연습 필요.
