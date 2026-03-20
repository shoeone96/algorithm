# Word Ladder - 패턴 Map을 활용한 암시적 그래프 BFS

> **문제**: [LeetCode 127. Word Ladder](https://leetcode.com/problems/word-ladder/)
> **유형**: 암시적 그래프 + BFS 최단거리
> **코드**: `src/y2026/mar/week3/WordLadder.java` (v1), `WordLadder2.java` (v2 패턴 Map)

---

## 핵심 아이디어: 패턴 Map으로 인접 관계 구성

### 문제점: 모든 쌍 비교는 O(N² x L)

```
for i in wordList:
    for j in wordList:
        if 한 글자만 다르면 → 인접리스트에 추가
```

단어 N개, 길이 L일 때 **O(N² x L)** — 단어가 많아지면 비효율적.

### 해결: 와일드카드 패턴을 키로 사용 → O(N x L)

```
"hot" → "*ot", "h*t", "ho*"
"dot" → "*ot", "d*t", "do*"
"lot" → "*ot", "l*t", "lo*"
```

같은 패턴 키에 모인 단어들 = **서로 한 글자만 다른 단어들**

```java
Map<String, List<String>> map = new HashMap<>();
for (String word : wordList) {
    for (int i = 0; i < word.length(); i++) {
        String key = word.substring(0, i) + "*" + word.substring(i + 1);
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
    }
}
```

### BFS에서 이웃 조회

```java
// 현재 단어에서 패턴 만들기 → Map에서 O(1) 조회
for (int i = 0; i < word.length(); i++) {
    String key = word.substring(0, i) + "*" + word.substring(i + 1);
    for (String neighbor : map.getOrDefault(key, List.of())) {
        if (!visited.contains(neighbor)) { ... }
    }
}
```

---

## 설계 포인트

### 1. 큐에 인덱스 대신 단어 자체를 넣기

- `Queue<String>` + `Set<String>` visited
- `indexOf()` 호출 불필요 → O(N) 탐색 제거
- 코드가 훨씬 단순해짐

### 2. 레벨 단위 BFS로 거리 관리

```java
int depth = 1;
while (!queue.isEmpty()) {
    int size = queue.size();
    for (int s = 0; s < size; s++) {
        // 같은 레벨 처리
    }
    depth++;
}
```

- `int[]`로 거리를 같이 넣을 필요 없음
- 레벨이 끝날 때 depth++ 하면 자동으로 최단 거리

### 3. beginWord 처리

- beginWord도 패턴 Map 조회로 이웃을 찾음
- 별도의 `isValidated()` 불필요

---

## 시간복잡도 비교

| 방식 | 인접 관계 구성 | BFS 이웃 조회 | 전체 |
|------|---------------|--------------|------|
| 모든 쌍 비교 (v1) | O(N² x L) | O(1) (인접리스트) | O(N² x L) |
| 패턴 Map (v2) | O(N x L) | O(L) (패턴 생성 + 조회) | O(N x L) |

---

## 실수 기록

1. **문제 해석 오류**: "한 글자만 다르다" = 같은 위치에서 한 글자만 다른 것. 문자 구성(anagram)이 아님.
2. **답 정의 오류**: 반환값이 경로의 **단어 개수** (beginWord 포함). 초기 거리를 1이 아닌 2로 설정해야 함.
3. **isValidated 이중 루프**: 인덱스 기반 비교가 아닌 모든 문자쌍 비교 → 중복 문자에서 오작동.
4. **indexOf 반복 호출**: 루프 안에서 O(N) 연산 3번 호출 → 성능 저하. 자료구조(Set) 선택으로 해결.

---

## 관련 문제: 변환 키로 Map 그루핑 패턴

"모든 쌍 비교 O(N²)" 대신 **각 항목을 변환 키로 매핑 → Map으로 그루핑**하는 패턴.

| 문제 | 변환 키 | Map 구조 |
|------|---------|---------|
| [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/) | 정렬된 문자열 ("eat" → "aet") | `Map<String, List<String>>` |
| [249. Group Shifted Strings](https://leetcode.com/problems/group-shifted-strings/) | 연속 문자 차이 (mod 26) | `Map<String, List<String>>` |
| [890. Find and Replace Pattern](https://leetcode.com/problems/find-and-replace-pattern/) | 첫 등장 순서로 정규화 ("mee" → "0,1,1") | 정규화 키 비교 |
| [205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/) | 첫 등장 순서 인코딩 | 양방향 Map |
| [290. Word Pattern](https://leetcode.com/problems/word-pattern/) | 패턴-단어 양방향 매핑 | `Map<Char, String>` + 역방향 |

**공통점**: O(N²) 쌍 비교 대신 **각 항목을 정규화 키로 변환 → Map에서 O(1) 그루핑/조회**.
49, 249번이 Word Ladder의 와일드카드 Map과 가장 유사한 버킷 그루핑 방식.
