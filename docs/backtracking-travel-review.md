# Travel 문제 풀이 회고 - 백트래킹 핵심 개념 정리

> **문제**: 세계일주 (Travel) - 예산 내에서 최대한 많은 나라 방문
> **유형**: 백트래킹 (완전탐색 + 가지치기)
> **코드**: `Travel.java` (v1), `Travel2.java` (v2)

---

## 1. 방문 추적 방법 선택: visited 배열

**고민**: 어떤 나라를 방문했는지 어떻게 추적할까?

- 방문 순서가 자유로움 (1→3→2도 가능, 2→1→3도 가능)
- `boolean[] isVisited`로 방문 여부를 관리

**조합 vs visited**:
- 조합(combination)은 "어떤 원소를 고를지"를 인덱스 순서대로 결정
- visited는 "아무 순서로든 방문 가능" → 순열(permutation) 구조에 자연스러움
- 이 문제는 방문 순서에 따라 결과가 달라질 수 있으므로 visited가 적합

---

## 2. 백트래킹 패턴: 선택 → 재귀 → 복원

```
isVisited[i] = true;   // 선택
dfs(...);               // 탐색
isVisited[i] = false;   // 복원 (원상복구)
```

- 복원을 빠뜨리면 다른 경우의 수를 탐색할 때 이미 방문한 것으로 판단
- 예: 1→2→3 탐색 후 복원 없으면, 1→3→2 경로에서 2,3이 이미 방문 상태

---

## 3. `count++` vs `++count` vs `count + 1`

재귀 호출 시 "방문 국가 수"를 다음 단계로 넘기는 3가지 방법:

| 표현        | 전달값    | 원본 변경 | 문제점                                      |
|-------------|-----------|-----------|---------------------------------------------|
| `count++`   | count     | O (후위)  | 현재 값(증가 전)이 전달됨. 의도와 다름      |
| `++count`   | count + 1 | O (전위)  | 값은 맞지만 원본이 바뀜 → for문 다음 반복에서 꼬임 |
| `count + 1` | count + 1 | X         | 원본 불변, 가장 안전                        |

**결론**: 재귀 매개변수에는 `count + 1` 사용이 가장 안전하다.

```java
// v1, v2 모두 이 방식 사용
dfs(left, countries, count + 1, isVisited);
```

---

## 4. 값 전달 방식: 원본 수정 vs 매개변수 계산

**v1 (원본 수정 + 백트래킹 복원)**:
```java
left -= countries[i][0];          // 원본 수정
dfs(left, countries, count + 1, isVisited);
left += countries[i][0];          // 복원
```

**v2 (매개변수에서 계산)**:
```java
dfs(left - countries[i][0], countries, count + 1, isVisited);
// 복원 불필요 - left 원본이 변하지 않음
```

**핵심 차이**:
- v1: 변수를 직접 바꾸고 되돌림 → 복원 빼먹으면 버그
- v2: 계산 결과를 매개변수로 넘김 → 원본 불변, 복원 불필요

**그런데 visited는?**:
- `boolean[]`은 참조 타입 → 매개변수로 넘겨도 같은 배열을 가리킴
- `isVisited[i] = true`를 매개변수 계산으로 대체할 수 없음
- 따라서 visited는 항상 **백트래킹 패턴(선택→재귀→복원)이 필수**

> **원칙**: 기본형(int, long)은 매개변수 계산으로 넘기면 깔끔.
> 참조형(배열, 객체)은 백트래킹 복원이 필수.

---

## 5. answer 갱신 위치: for문 안 vs 밖

**for문 안에서 갱신하면?**:
```java
for (int i = 0; i < countries.length; i++) {
    if (!isVisited[i] && left >= countries[i][1]) {
        answer = Math.max(answer, count + 1);  // 여기서 갱신
        isVisited[i] = true;
        dfs(...);
        isVisited[i] = false;
    }
}
```
- 방문 가능한 나라를 만날 때마다 중복 실행
- 아직 탐색하지 않은 나라가 남아있을 수 있는데 미리 갱신

**v1 (for문 밖에서 갱신)**:
```java
private void dfs(int left, int[][] countries, int count, boolean[] isVisited) {
    // ... for문으로 더 깊이 탐색 ...

    answer = count;  // for문 끝나고 갱신
}
```

**v2 (진입 시 갱신)**:
```java
private void dfs(int left, int[][] countries, int count, boolean[] isVisited) {
    answer = Math.max(answer, count);  // 진입하자마자 갱신

    for (int i = 0; i < countries.length; i++) { ... }
}
```

**왜 이게 맞을까?**:
1. 재귀는 깊은 곳부터 실행 완료됨 (예: count=3 → count=2 → count=1)
2. 깊은 곳에서 `answer = 3`으로 갱신
3. 얕은 곳으로 돌아왔을 때 `Math.max(3, 2)` → 3 유지, 덮어쓰지 않음
4. v2처럼 `Math.max`를 쓰면 항상 최대값만 유지되어 안전

**v1의 문제**: `answer = count`는 max 비교 없이 무조건 대입 → 마지막에 호출된 값으로 덮어씀.
실제로는 for문이 끝난 시점에 count를 대입하므로, 더 깊은 탐색 결과가 덮어써질 수 있음.

---

## 6. static 필드 초기화

```java
private static int answer = -1;

public int solution(int balance, int[][] countries) {
    answer = -1;  // 매 호출마다 초기화 필수!
    // ...
}
```

**왜 필요한가?**:
- `static`은 클래스에 귀속 → 인스턴스가 아닌 클래스 레벨에서 공유
- `solution()`을 여러 번 호출하면 이전 결과가 `answer`에 남아있음
- 초기화 없으면 두 번째 호출부터 이전 값이 섞여서 오답

**예시**:
```
1차 호출: answer = 3 (정상)
2차 호출: answer가 3인 채로 시작 → 오답 가능
```

---

## 요약: v1 vs v2 비교

| 항목              | v1 (Travel.java)                    | v2 (Travel2.java)                  |
|-------------------|-------------------------------------|-------------------------------------|
| left 전달         | 원본 수정 + 복원                    | 매개변수 계산 (원본 불변)           |
| answer 갱신       | for문 밖, 무조건 대입               | 진입 시, Math.max로 비교            |
| visited 처리      | 백트래킹 (동일)                     | 백트래킹 (동일)                     |
| 코드 간결성       | 복원 코드 필요                      | 더 간결                            |
| 버그 가능성       | 복원 누락 위험                      | 낮음                               |

**v2가 더 안전하고 간결한 패턴.** 기본형은 매개변수 계산으로, 참조형은 백트래킹 복원으로.
