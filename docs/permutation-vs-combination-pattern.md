# 순열 vs 조합: 재귀 구조의 핵심 차이

> **문제**: Subsets (부분집합 구하기)
> **깨달음**: 조합에서는 이미 탐색이 끝난 범위로 다시 돌아가지 않는다
> **코드**: `Subsets.java`

---

## 핵심 원칙

- **순열(Permutation)**: 순서가 다르면 다른 결과 → `{1,2}` != `{2,1}`
- **조합(Combination)**: 순서 상관없음 → `{1,2}` == `{2,1}`

이 차이가 재귀 구조를 결정한다.

---

## 재귀 구조 비교

### 순열: 매번 처음부터 + visited

```java
// 매번 i=0부터 전체 순회, visited로 중복 방지
private void dfs(int[] nums, boolean[] visited, List<Integer> temp) {
    for (int i = 0; i < nums.length; i++) {
        if (!visited[i]) {
            visited[i] = true;
            temp.add(nums[i]);
            dfs(nums, visited, temp);
            visited[i] = false;
            temp.removeLast();
        }
    }
}
```

- `i=0`부터 시작 → 이전 원소도 다시 선택 가능
- `visited`가 필수 (같은 원소 중복 선택 방지)

### 조합: startIndex로 앞으로만 진행

```java
// start부터 순회 → 이미 지나간 원소는 다시 보지 않음
private void dfs(int[] nums, List<Integer> temp, int start) {
    for (int i = start; i < nums.length; i++) {
        temp.add(nums[i]);
        dfs(nums, temp, i + 1);
        temp.removeLast();
    }
}
```

- `i=start`부터 시작 → 이전 원소로 절대 돌아가지 않음
- `visited` 불필요 (앞으로만 가니까 중복이 구조적으로 불가능)

---

## 왜 조합에서 startIndex가 동작하는가?

`[1, 2, 3]`에서 부분집합을 구할 때:

```
start=0: 1 선택 → start=1로 재귀
  start=1: 2 선택 → start=2로 재귀
    start=2: 3 선택 → {1,2,3}
  start=2: 3 선택 → {1,3}
start=1: 2 선택 → start=2로 재귀
  start=2: 3 선택 → {2,3}
start=2: 3 선택 → {3}
```

- `2`를 선택한 시점에서 `1`은 이미 선택/비선택이 확정됨
- 뒤로 돌아가면 `{2,1}`이 생기지만, `{1,2}`와 같은 집합 → 불필요한 탐색

---

## 중복 허용 조합 (Combination Sum)

> **문제**: Combination Sum (LeetCode 39)
> **깨달음**: 같은 원소를 반복 사용할 수 있는 조합은 `i+1`이 아니라 `i`를 넘기면 된다
> **코드**: `CombinationSum2.java`

### 핵심: 재귀 호출 시 startIndex를 `i`로 넘기기

```java
private void dfs(List<List<Integer>> answer, int[] candidates, int target,
                 List<Integer> temp, int sum, int startIndex) {
    if (sum >= target) {
        if (sum == target) answer.add(List.copyOf(temp));
        return;
    }
    for (int i = startIndex; i < candidates.length; i++) {
        temp.add(candidates[i]);
        sum += candidates[i];
        dfs(answer, candidates, target, temp, sum, i);  // i+1이 아니라 i
        temp.removeLast();
        sum -= candidates[i];
    }
}
```

### 왜 동작하는가?

`candidates = [2, 3, 6, 7]`, `target = 7`일 때:

- `i`를 넘기면 → 자기 자신을 다시 선택 가능 (2→2→2→... 반복 허용)
- `startIndex` 이전으로는 돌아가지 않음 → `[3,2]` 같은 순서 중복 방지

```
dfs(i=0): 2 선택 → dfs(i=0): 2 선택 → dfs(i=0): 2 선택 → dfs(i=0): 합 초과, stop
                                     → dfs(i=1): 3 선택 → 합=7 ✅ {2,2,3}
         → dfs(i=1): 3 선택 → ...
```

### startIndex 변형 정리

| 상황 | 재귀 호출 시 전달 | 예시 |
|------|-------------------|------|
| 원소 중복 사용 불가 (부분집합) | `i + 1` | Subsets, Subsets II |
| 원소 중복 사용 허용 (조합) | `i` | Combination Sum |
| 순서가 의미 있음 (순열) | `0` + visited | Permutations |

---

## 판단 기준 요약

| 질문                        | 순열              | 조합              |
|-----------------------------|-------------------|-------------------|
| 순서가 의미 있는가?         | O                 | X                 |
| 탐색 시작점                 | 항상 `i=0`        | `i=start`         |
| visited 필요?               | O                 | X                 |
| 중복 방지 방법              | visited 배열      | startIndex        |
| 탐색 공간                   | N!                | 2^N               |
