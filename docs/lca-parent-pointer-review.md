# LCA (Lowest Common Ancestor) - 부모 포인터 + Set 풀이

> **문제**: 폴더 바이러스 (Folder) - 두 감염 폴더의 최소 공통 상위 폴더 찾기
> **유형**: 트리 - LCA (최소 공통 조상)
> **코드**: `src/y2026/mar/week4/Folder.java`

---

## LCA란?

두 노드를 **동시에 포함하는 가장 가까운(가장 낮은) 공통 조상**.

```
        root
         |
        media
       /     \
    images   videos
      |        |
   holiday  concert
```

`holiday`와 `concert`의 LCA → `media`

---

## 핵심 아이디어: 관계 역전 + Set 비교

### 1단계: 부모 → 자식 관계를 뒤집기

입력이 `[부모, 자식]` 쌍으로 주어지지만, 아래에서 위로 올라가야 하므로 **자식 → 부모** Map을 만든다.

```java
Map<String, String> map = new HashMap<>();
for (String[] folder : folders) {
    map.put(folder[1], folder[0]);  // 자식 → 부모
}
```

### 2단계: p에서 루트까지 경로를 Set에 저장

```java
Set<String> set = new HashSet<>();
set.add(start1);  // 자기 자신도 포함 (자신이 LCA일 수 있음)
while (map.get(start1) != null) {
    set.add(map.get(start1));
    start1 = map.get(start1);
}
```

`"holiday"` 기준: `Set = {"holiday", "images", "media", "root"}`

### 3단계: q에서 올라가며 Set에 처음 겹치는 노드 = LCA

```java
if (set.contains(start2)) return start2;  // q 자체가 LCA인 경우
while (map.get(start2) != null) {
    String parent = map.get(start2);
    if (set.contains(parent)) return parent;
    start2 = map.get(start2);
}
```

`"concert"` → `"videos"` (없음) → `"media"` (있음!) → **LCA = "media"**

---

## 왜 BFS/DFS가 필요 없는가

- 부모 포인터(`자식 → 부모` Map)가 있으면 **위로 올라가는 것 자체가 탐색**
- while문 하나로 루트까지 도달 가능 — 트리 높이 H만큼만 반복
- Set의 `contains()`는 O(1)이므로 전체 시간복잡도: **O(H)**

---

## 시간/공간 복잡도

| | 복잡도 |
|--|--------|
| Map 구성 | O(N) — N: 폴더 쌍 개수 |
| p 경로 수집 | O(H) — H: 트리 높이 |
| q 경로 탐색 | O(H) |
| **전체** | **O(N + H)** |
| 공간 | O(N) — Map + Set |

---

## 실수 기록

1. **computeIfAbsent vs computeIfPresent 혼동**: `computeIfPresent`는 키가 이미 있을 때만 동작. 새로 넣을 때는 `computeIfAbsent`.
2. **computeIfAbsent 람다 반환값 실수**: `k -> new ArrayList<>().add(x)`는 boolean을 반환. `k -> new ArrayList<>()`로 리스트만 반환하고 `.add()`는 밖에서 체이닝.
3. **람다 내 for문 변수 사용 불가**: 람다에서는 effectively final 변수만 사용 가능. for문의 `i`는 변하므로 별도 변수로 추출 필요.
4. **Map 방향 설계**: 부모 → 자식(`Map<String, List<String>>`)이 아니라, 문제에 맞게 자식 → 부모(`Map<String, String>`)로 뒤집어야 했음.
5. **start1/start2 변수 혼동**: 두 번째 while문에서 start1을 써서 q 대신 p를 계속 조회하는 실수.
6. **자기 자신이 LCA인 케이스 누락**: q 자체가 p의 경로에 포함될 수 있으므로, while문 전에 `set.contains(start2)` 체크 필요.

---

## LCA 패턴 변형

| 상황 | 접근법 |
|------|--------|
| 부모 포인터가 있을 때 | Set 비교 (이 문제) |
| 이진 트리 + 루트만 주어질 때 | 재귀 DFS (LeetCode 236) |
| 대량 LCA 쿼리 | Binary Lifting / Euler Tour + RMQ |

---

## 관련 문제

| 문제 | 핵심 |
|------|------|
| [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) | 재귀 DFS로 LCA — 부모 포인터 없는 버전 |
| [1644. Lowest Common Ancestor of a Binary Tree II](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/) | 노드가 존재하지 않을 수 있는 LCA |
| [1650. Lowest Common Ancestor of a Binary Tree III](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/) | 부모 포인터 있는 LCA — 이 문제와 동일 패턴 |
| [1676. Lowest Common Ancestor of a Binary Tree IV](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/) | 여러 노드의 LCA |

---

## 오늘의 학습 포인트

1. **트리에서 관계 역전**: 입력이 부모 → 자식이어도, 문제에 따라 자식 → 부모로 뒤집어 생각할 수 있다
2. **BFS/DFS 없이도 트리 문제를 풀 수 있다**: 부모 포인터가 있으면 단순 while문으로 충분
3. **Set을 활용한 경로 비교**: 한쪽 경로를 Set에 담고 다른 쪽에서 올라가며 O(1) 비교 — LinkedList 교차점 찾기와 유사한 사고방식
