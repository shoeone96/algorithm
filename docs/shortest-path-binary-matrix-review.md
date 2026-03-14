# Shortest Path in Binary Matrix 풀이 회고 - 단일 출발점 BFS

> **문제**: Shortest Path in Binary Matrix (LeetCode 1091)
> **유형**: BFS (Grid, 8방향)
> **코드**: `ShortestPathInBinaryMatrix.java`

---

## 1. 핵심 아이디어

(0,0)에서 (n-1,n-1)까지 0으로만 이루어진 최단 경로의 길이를 구하는 문제.

- 시작점과 끝점이 명확한 **단일 출발점 BFS**
- 8방향 이동 (상하좌우 + 대각선 4방향)
- BFS 특성상 **처음 도착 = 최단 거리** → 도착 즉시 return

## 2. 예외 처리

- `grid[0][0] == 1` 또는 `grid[n-1][n-1] == 1`이면 경로 불가 → 즉시 `-1` 반환

## 3. 리팩토링 포인트

### 불필요한 answer 변수 제거
- 처음에 `answer = Integer.MAX_VALUE`로 두고 최솟값 비교
- BFS는 처음 도착이 곧 최단 거리 → `answer` 변수 없이 바로 `return poll[2]`로 충분

## 4. 사고 패턴 정리: 단일 BFS vs 멀티소스 BFS

| 상황 | 접근법 |
|------|--------|
| 시작점 → 도착점 최단 거리 (이 문제) | 단일 출발점 BFS |
| 모든 셀의 최단 거리 필요 (01 Matrix) | 역방향 멀티소스 BFS |

**판별 기준**: "각 셀마다 BFS를 돌아야 할 것 같다" → 반대편에서 멀티소스 BFS 고려
"시작점과 끝점이 하나씩 정해져 있다" → 단일 BFS로 충분
