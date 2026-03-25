# Isomorphic Strings - containsValue 트레이드오프 판단

> **문제**: [LeetCode 205. Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/)
> **유형**: HashMap 양방향 매핑 (Bijection) — LC 290 Word Pattern과 동일 패턴
> **코드**: `src/y2026/mar/week4/IsomorphicStrings.java`

---

## 핵심 아이디어

두 문자열 s, t가 동일한 매핑 패턴(isomorphic)인지 확인.
s의 각 문자 → t의 문자로 1:1 대응이 성립해야 함.

```
s = "egg", t = "add"
e → a, g → d  ✅ (1:1 대응)

s = "foo", t = "bar"
f → b, o → a, o → r?  ❌ (o가 a와 r 두 곳에 매핑)
```

---

## 풀이: 단방향 Map + containsValue

```java
Map<Character, Character> map = new HashMap<>();
for (int i = 0; i < length; i++) {
    if (!map.containsKey(s.charAt(i))) {
        if (map.containsValue(t.charAt(i))) return false;  // 역방향 중복 체크
        map.put(s.charAt(i), t.charAt(i));
        continue;
    }
    if (!map.get(s.charAt(i)).equals(t.charAt(i))) return false;
}
```

- `containsKey`: s→t 매핑이 이미 있으면 일치 확인
- `containsValue`: 새 매핑 추가 전, t 쪽 문자가 이미 다른 s 문자에 매핑되어 있는지 확인 (역방향)

---

## containsValue vs 양방향 Map 트레이드오프

### 이론적 차이
| 방식 | 시간복잡도 | 공간 |
|------|-----------|------|
| containsValue | O(n × m) (m = Map 크기) | Map 1개 |
| 양방향 Map (s→t, t→s) | O(n) | Map 2개 |

### 실전 판단: containsValue가 더 낫다

**이유**: 입력 도메인이 bounded.
- 문자 종류는 ASCII 범위(최대 128개)로 고정
- Map 크기가 상수로 bounded → containsValue 순회 비용이 사실상 O(1)
- 실측 성능 차이 없음 (직접 Set 방식으로 테스트 후 확인)

**장점**:
- 자료구조 1개만 관리 → 코드 단순성, 가독성
- 실질적 성능 동일

**면접 답변 포인트**: "문자 종류가 상수로 bounded라서 containsValue 순회가 사실상 O(1). 자료구조를 하나 더 추가하는 것보다 코드 단순성이 더 이득."

---

## LC 290 Word Pattern과의 관계

| | LC 205 Isomorphic Strings | LC 290 Word Pattern |
|---|---|---|
| 매핑 대상 | char → char | char → String |
| 도메인 크기 | bounded (ASCII) | unbounded (단어 종류 제한 없음) |
| containsValue | 사실상 O(1) | O(n) — **양방향 Map 필요** |

**핵심**: 같은 Bijection 패턴이지만, **도메인 크기에 따라 최적 전략이 달라진다.**

---

## 시간복잡도

- O(n) — n은 문자열 길이 (containsValue가 상수이므로)
- 공간: O(1) — Map 크기가 상수(문자 종류) bounded
