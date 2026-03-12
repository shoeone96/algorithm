# Rotten Oranges 풀이 회고 - 멀티소스 BFS

> **문제**: Rotting Oranges (LeetCode 994)
> **유형**: BFS (Grid, 멀티소스)
> **코드**: `RottenOrange.java`

---

## 1. 핵심 아이디어

모든 썩은 오렌지(2)가 **동시에** 퍼져나가는 문제 → **멀티소스 BFS**

- BFS 시작 전, 모든 시작점(값이 2인 셀)을 큐에 미리 넣는다
- `queue.size()`를 이용해 **레벨(분) 단위**로 처리한다

## 2. 레벨 단위 BFS 구조

```
queue에 모든 시작점(2) 넣기

while (!queue.isEmpty()):
    size = queue.size()        // 현재 레벨 개수 저장
    for (i = 0; i < size; i++):
        poll → 4방향 탐색 → fresh이면 2로 변경 후 offer
    // 여기가 1분 경과 시점
```

**포인트**: for문 안에서 새로 offer된 것들은 `size`에 포함되지 않으므로, 자연스럽게 다음 레벨로 분리된다.

## 3. 카운트 엣지케이스 — isOfferedAny 플래그

BFS는 시작점을 먼저 큐에 넣고 시작하기 때문에, 마지막 레벨에서 전파 없이도 while문이 한 번 더 돈다.

- `[[2]]` → 전파 없음 → 정답 0이어야 하는데 단순 count++이면 1이 됨
- **해결**: 실제로 `queue.offer`가 발생했을 때만 count 증가

```java
boolean isOfferedAny = false;
for (int i = 0; i < size; i++) {
    // 탐색 후 offer 시
    isOfferedAny = true;
}
if (isOfferedAny) answer++;
```

## 4. 전체 흐름 정리

1. 모든 2를 큐에 넣기 (멀티소스)
2. 레벨 단위 BFS — `queue.size()`로 분 단위 구분
3. fresh(1) → 2로 변경 + offer, 플래그로 전파 여부 추적
4. 레벨 끝날 때 전파가 있었으면 answer++
5. BFS 종료 후 grid에 1이 남아있으면 -1 리턴

## 5. 삽질 과정에서 배운 것

- **처음 접근**: 각 썩은 오렌지마다 독립 BFS → 동시 전파 미반영 + 방문 체크 누락으로 시간초과
- **개선 1**: 멀티소스 BFS로 전환 (모든 2를 한 큐에)
- **개선 2**: `queue.size()` 레벨 단위 처리로 "분" 개념 구현
- **개선 3**: `isOfferedAny` 플래그로 마지막 레벨 카운트 오류 해결

## 6. 패턴 정리

| 패턴 | 적용 |
|------|------|
| 멀티소스 BFS | 시작점이 여러 개일 때, 모두 큐에 넣고 시작 |
| 레벨 단위 처리 | `queue.size()` 저장 후 그만큼만 poll → 시간/거리 단위 구분 |
| 전파 플래그 | BFS 카운트 시 실제 변화가 있었는지 확인 → 엣지케이스 방지 |
