# 이분 그래프 판별 풀이 회고 - BFS/DFS 2-Coloring

> **문제**: 홍팀/청팀 (강의) + Is Graph Bipartite? (LeetCode 785)
> **유형**: BFS/DFS (이분 그래프 판별)
> **코드**: `RedBlueTeam.java` (BFS), `IsGraphBipartite.java` (DFS)

---

## 1. 핵심 아이디어

"같은 팀끼리 친구가 아니어야 한다" = **이분 그래프 판별**

- 각 노드에 0 또는 1 (두 가지 색)을 배정
- 인접 노드는 반드시 **반대 색**이어야 함
- 인접 노드가 **같은 색**이면 이분 그래프가 아님 → `false`

## 2. 접근법: 2-Coloring

```
1. color 배열을 -1(미배정)로 초기화
2. 모든 노드에 대해 (연결 안 된 컴포넌트 처리):
   - 미배정이면 색 0 배정 후 BFS/DFS 시작
3. 탐색 중:
   - 인접 노드가 같은 색 → false
   - 인접 노드가 미배정 → 반대 색(1 - 현재색) 배정 후 탐색 계속
```

**반대 색 배정 트릭**: `1 - color[현재]`로 0↔1 전환

## 3. BFS 버전 (RedBlueTeam.java)

```java
private boolean bfs(int start, int[][] friends, int[] color) {
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(start);
    while (!queue.isEmpty()) {
        Integer poll = queue.poll();
        for (int ele : friends[poll]) {
            if (color[ele] == color[poll]) return false;  // 같은 색 → 불가능
            if (color[ele] == -1) {
                color[ele] = 1 - color[poll];  // 반대 색 배정
                queue.offer(ele);
            }
        }
    }
    return true;
}
```

## 4. DFS 버전 (IsGraphBipartite.java)

```java
private boolean dfs(int start, int[][] graph, int[] group) {
    for (int ele : graph[start]) {
        if (group[ele] == group[start]) return false;
        if (group[ele] == -1) {
            group[ele] = 1 - group[start];
            if (!dfs(ele, graph, group)) return false;  // 반환값 체크 필수!
        }
    }
    return true;
}
```

## 5. 삽질 과정에서 배운 것

### 첫 시도: 연결 여부만 체크 (틀린 접근)
- BFS로 방문 여부(`boolean[] isVisited`)만 체크
- 이분 그래프라는 개념을 모르면 "두 팀으로 나누기"를 어떻게 접근할지 감이 안 잡힘
- **교훈**: 문제의 키워드("두 그룹으로 나누기", "같은 그룹 내 관계 없음")를 보면 이분 그래프를 떠올릴 것

### DFS 반환값 무시 버그
- `dfs(ele, graph, group);`으로 반환값을 무시
- 하위 탐색에서 `false`가 나와도 상위에서 모르고 `true` 반환
- **수정**: `if (!dfs(ele, graph, group)) return false;`

## 6. BFS vs DFS 비교

| 항목 | BFS (RedBlueTeam) | DFS (IsGraphBipartite) |
|------|-------------------|------------------------|
| 자료구조 | Queue | 재귀 스택 |
| 색 배정 | poll 후 인접 노드에 배정 | 현재 노드에서 인접 노드에 배정 |
| 주의점 | - | 재귀 반환값 반드시 체크 |
| 복잡도 | O(V+E) | O(V+E) |

## 7. 패턴 정리

| 패턴 | 적용 |
|------|------|
| 이분 그래프 판별 | "두 그룹/팀으로 나눌 수 있는가?" 키워드 |
| 2-Coloring | `int[] color`로 색 배정, `1 - color`로 반대 색 전환 |
| 다중 컴포넌트 처리 | for문으로 모든 노드 순회, 미배정이면 새 탐색 시작 |
| DFS 반환값 전파 | 재귀 호출 결과를 반드시 체크하고 전파할 것 |
