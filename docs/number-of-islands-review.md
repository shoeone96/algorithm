# Number of Islands 풀이 회고 - DFS 그래프 탐색

> **문제**: Number of Islands (LeetCode 200)
> **유형**: DFS (그래프/Grid 탐색)
> **코드**: `NumberOfIsland.java` (v1, visited 배열), `NumberOfIsland2.java` (v2, grid 직접 변경)

---

## 1. v1에서 발견한 버그 2개

### 버그 1: char와 int 비교

```java
// 틀림
if (grid[i][j] == 0)    // int 0과 비교 → char '0'(ASCII 48)과 불일치
if (grid[i][j] == 1)    // int 1과 비교 → char '1'(ASCII 49)과 불일치

// 올바름
if (grid[i][j] == '0')
if (grid[i][j] == '1')
```

`char[][]`이면 반드시 `'0'`, `'1'`로 비교해야 한다.

### 버그 2: 반복 범위 누락

```java
// 틀림
for (int i = 0; i < grid.length - 1; i++)  // 마지막 행 탐색 안 됨

// 올바름
for (int i = 0; i < grid.length; i++)
```

`length - 1`은 마지막 인덱스지, 반복 상한이 아니다.

---

## 2. v1 vs v2 비교

| 항목 | v1 (NumberOfIsland.java) | v2 (NumberOfIsland2.java) |
|------|--------------------------|---------------------------|
| 방문 처리 | `boolean[][] isVisited` 별도 배열 | `grid[r][c] = '0'`으로 원본 변경 |
| 메모리 | O(M*N) 추가 | 추가 없음 |
| 원본 보존 | O | X |
| 코드량 | 전처리(0 마킹) 필요 | 간결 |

---

## 3. 원본 변경 방식의 사용 판단

grid를 직접 변경해도 되는 경우:
- 섬 개수 세기처럼 **한 번 탐색하고 끝**인 경우

grid를 변경하면 안 되는 경우:
- **백트래킹**으로 여러 경로를 탐색해야 할 때 (경로 복원 필요)
- **BFS 최단거리** 탐색 시 경로 정보가 필요할 때
- 이후 로직에서 원본 grid를 다시 참조해야 할 때

> **원칙**: "이 탐색 이후에 원본이 필요한가?"를 기준으로 판단

---

## 4. 디버깅 교훈 (GenerateParentheses에서 이어짐)

이번에도 동일한 패턴 — 설계는 맞았지만 구현에서 실수:
- `char` vs `int` 비교 실수
- `length` vs `length - 1` 범위 실수

**코드 작성 후 작은 케이스로 코드 기준 손 트레이싱**을 했다면 바로 잡을 수 있었던 버그들.

---

## 5. 인접행렬 읽는 법 (Number of Provinces)

인접행렬 `isConnected[i][j]`에서 헷갈리기 쉬운 포인트:

- **row/col 인덱스 = 정점 번호** (i, j가 노드)
- **값 = 두 정점의 관계** (0 또는 1 = 연결 여부)

```java
// 인접행렬 직접 순회
for (int i = 0; i < connectList[start].length; i++) {
    if (!isVisited[i] && connectList[start][i] == 1) {  // i가 다음 노드
        dfs(i, isVisited, connectList);
    }
}
```

`for (int next : connectList[start])`처럼 enhanced for를 쓰면 **값(0/1)**이 순회되므로, 인덱스 기반 for문을 써야 **노드 번호**를 얻을 수 있다.

> **핵심**: 인접행렬에서는 "인덱스가 노드, 값은 관계"라는 점을 항상 의식할 것.
