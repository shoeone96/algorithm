# 중복 원소가 있는 조합/순열: 가지치기 전략

> **핵심 원칙**: 중복 제거는 후처리보다 사전 차단이 낫다
> **전제조건**: `Arrays.sort(nums)` → 중복 원소가 인접하게 정렬

---

## 1. 중복 제거 전략 비교 (후처리 → 사전 차단)

| 전략 | 방식 | 성능 | 단점 |
|------|------|------|------|
| List + contains | 모든 결과 생성 후 List에서 중복 검색 | 36ms | `contains()`가 O(n) 순회 |
| HashSet | 모든 결과 생성 후 Set으로 중복 제거 | 12ms | 불필요한 탐색은 여전히 발생 |
| **정렬 + 가지치기** | **DFS 단계에서 중복 탐색 자체를 차단** | **2~3ms** | **없음 (최적)** |

---

## 2. 조합/부분집합의 가지치기 (Subsets II)

> **문제**: Subsets II (LeetCode 90)
> **코드**: `Subsets2.java` (v1) → `Subsets2_1.java` (v2) → `Subsets2_2.java` (v3)

### 가지치기 조건

```java
for (int i = startIndex; i < nums.length; i++) {
    if (i > startIndex && nums[i] == nums[i - 1]) continue;  // 핵심
    temp.add(nums[i]);
    dfs(answer, nums, i + 1, temp);
    temp.removeLast();
}
```

### 왜 동작하는가?

`nums = [1, 4, 4, 4, 4]` (정렬 후), `startIndex = 1`일 때:
- `i=1`: `4`를 선택 → 이후 `[4]`, `[4,4]`, `[4,4,4]` 등 모든 조합 탐색
- `i=2`: 또 `4`를 선택 → 이후 만들어지는 조합은 i=1의 **부분집합**
- `i=3`, `i=4`도 마찬가지

**같은 depth에서 같은 값의 탐색은 이전 탐색의 부분집합이므로 무조건 중복.**

### 정렬하면 매번 정렬이 불필요

- 정렬 후 조합(startIndex)으로 탐색하면 temp는 항상 정렬 상태 유지
- `.stream().sorted().toList()` 불필요 → 성능 개선

### continue 위치가 중요한 이유

```java
// O: continue로 add/remove 자체를 건너뜀
if (i > startIndex && nums[i] == nums[i - 1]) continue;
temp.add(nums[i]);
dfs(...);
temp.removeLast();

// X: add/remove는 매번 실행됨 (불필요한 연산)
temp.add(nums[i]);
if (i == startIndex || nums[i] != nums[i - 1]) {
    dfs(...);
}
temp.removeLast();
```

---

## 3. Combination Sum II — 같은 가지치기, 다른 문제

> **문제**: Combination Sum II (LeetCode 40)
> **코드**: `CombinationSumTwo.java`
> **깨달음**: Subsets II와 완전히 같은 원리. 같은 for문 안에서 이미 한번 돌았던 값과 같으면 스킵.

### 코드

```java
Arrays.sort(candidates);  // 전제조건: 정렬

for (int i = startIndex; i < candidates.length; i++) {
    if (i != startIndex && candidates[i] == candidates[i - 1]) continue;  // 동일한 조건
    sum += candidates[i];
    temp.add(candidates[i]);
    dfs(answer, candidates, target, temp, sum, i + 1);  // i+1: 각 원소 한 번만 사용
    sum -= candidates[i];
    temp.removeLast();
}
```

### Subsets II와의 공통점

**가지치기 조건이 완전히 동일하다.**

| | Subsets II | Combination Sum II |
|---|---|---|
| 가지치기 조건 | `i > startIndex && nums[i] == nums[i-1]` | `i != startIndex && candidates[i] == candidates[i-1]` |
| 재귀 호출 | `dfs(..., i + 1)` | `dfs(..., i + 1)` |
| 종료 조건 | 모든 부분집합 수집 | `sum == target` |

**핵심 원리**: 같은 depth(for문)에서 같은 값으로 두 번 분기하면, 두 번째는 첫 번째의 부분집합 → 무조건 중복.

---

## 4. 순열의 가지치기 (Permutations II)

> **문제**: Permutations II (LeetCode 47)
> **코드**: `Permutations2_2.java` (HashSet) → `Permutations2.java` (가지치기)

### 조합과의 핵심 차이

| | 조합 (Subsets) | 순열 (Permutations) |
|---|---|---|
| 탐색 시작점 | `i = startIndex` | `i = 0` (매번 처음부터) |
| 중복 방지 기본 도구 | startIndex | visited 배열 |
| 같은 depth 판별 | `i > startIndex` | `!isVisited[i-1]` |

### 가지치기 조건

```java
for (int i = 0; i < nums.length; i++) {
    if (isVisited[i] || (i > 0 && nums[i] == nums[i - 1] && !isVisited[i - 1])) continue;
    isVisited[i] = true;
    temp.add(nums[i]);
    dfs(nums, answer, temp, isVisited);
    temp.removeLast();
    isVisited[i] = false;
}
```

### `!isVisited[i-1]` vs `isVisited[i-1]` — 둘 다 정답이지만 효율이 다름

`nums = [1a, 1b, 2]`로 비교:

**`!isVisited[i-1]` (앞에서부터 순서 강제 — 더 효율적)**
- 1b를 쓰려면 1a가 **이미 사용 중**이어야 함
- depth 0에서 1b가 **바로 차단** → 2개 분기만 탐색

```
depth 0:
├─ 1a 허용 ✅ → 1b 허용 (1a가 사용 중이니까)
├─ 1b 차단 ❌ (1a가 아직 안 쓰였으니까)
└─ 2  허용 ✅ → 1a 허용 → 1b 허용 (1a가 사용 중이니까)
결과: [1a,1b,2], [1a,2,1b], [2,1a,1b]
```

**`isVisited[i-1]` (뒤에서부터 역순 강제 — 덜 효율적)**
- 1b를 쓰려면 1a가 **사용 중이 아니어야** 함
- depth 0에서 1b가 **허용** → 3개 분기 모두 탐색

```
depth 0:
├─ 1a 허용 ✅ → 1b 차단 (1a가 사용 중이니까) → 더 깊이 가서야 차단
├─ 1b 허용 ✅ → 1a 허용
└─ 2  허용 ✅ → 1b 허용 (1a가 안 쓰였으니까) → 1a 허용
결과: [1b,1a,2], [1b,2,1a], [2,1b,1a]
```

**둘 다 중복 원소의 사용 순서를 하나로 고정**하므로 결과는 동일.
하지만 `!isVisited[i-1]`이 **더 일찍 가지치기**하므로 탐색량이 적다.

---

## 4. HashSet의 O(1) 동작 원리

### 구조

```
HashSet 내부 = HashMap (key: 저장할 값, value: 더미 객체)
HashMap 내부 = 배열(버킷) + 해시 함수
```

### 동작 흐름

1. **add(element)**: `element.hashCode() % 배열크기` → 배열 인덱스(버킷) 계산 → 해당 버킷에 저장
2. **contains(element)**: 같은 해시 계산 → 해당 버킷에서만 `equals()` 비교

### 왜 O(1)인가?

- 배열 인덱스 접근 = O(1)
- 충돌이 적으면 버킷당 1~2개 → `equals()` 비교도 1~2번
- 전체 n개를 순회하는 `List.contains()`의 O(n)과 근본적으로 다름

### List를 HashSet에 넣을 때

- Java의 `List`는 `hashCode()`와 `equals()`를 **내용 기반**으로 오버라이딩
- 서로 다른 객체(다른 주소값)라도 내용이 같으면 같은 해시코드, 같은 equals 결과
- 단, `hashCode()`와 `equals()` 모두 원소 개수 k만큼 순회 → O(k)
- k가 작으면(이 문제에서 nums 최대 10) 상수 취급 가능
