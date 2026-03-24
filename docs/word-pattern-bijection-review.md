# Word Pattern - HashMap 1:1 매핑(Bijection) 패턴

> **문제**: [LeetCode 290. Word Pattern](https://leetcode.com/problems/word-pattern/)
> **유형**: HashMap 양방향 매핑 검증
> **코드**: `src/y2026/mar/week4/WordPattern.java` (v1 containsValue), `WordPattern2.java` (v2 양방향 Map), `WordPattern3.java` (v3 put 반환값)

---

## 핵심 아이디어: 1:1 대응(Bijection) 검증

패턴 문자와 단어가 **같은 문자 → 항상 같은 단어**, **다른 문자 → 반드시 다른 단어**를 만족하는지 확인.

단방향 매핑(문자→단어)만 체크하면 **다른 문자가 같은 단어를 가리키는 경우**를 놓침.

```
pattern = "abba", s = "dog dog dog dog"
a → dog ✅
b → dog?  ← 단방향만 보면 통과하지만, a가 이미 dog이므로 실패해야 함
```

---

## 사전 검증: 길이 불일치 처리

루프 전에 `pattern` 길이와 단어 개수가 다르면 즉시 `false` 반환.
- try-catch로 IndexOutOfBounds를 잡는 건 **예외를 흐름 제어로 쓰는 안티패턴**
- 길이가 다르면 1:1 대응 자체가 불가능 → 가장 먼저 걸러야 할 조건

---

## 풀이 3가지 비교

### v1. containsValue 방식 (깔끔, O(n²))

```java
Map<Character, String> map = new HashMap<>();
for (int i = 0; i < words.length; i++) {
    if (!map.containsKey(pattern[i])) {
        if (map.containsValue(words[i])) return false;  // 역방향 체크
        map.put(pattern[i], words[i]);
        continue;
    }
    if (!map.get(pattern[i]).equals(words[i])) return false;
}
```

- **장점**: 직관적이고 코드가 깔끔
- **단점**: `containsValue`가 O(n)이라 전체 O(n²)
- **판단**: pattern 최대 길이 300이면 실질적 문제 없음. **코테에서는 이 방식이 충분**

### v2. 양방향 Map 방식 (O(n), 조건문 복잡)

```java
Map<Character, String> map = new HashMap<>();
Map<String, Character> reverseMap = new HashMap<>();
for (int i = 0; i < words.length; i++) {
    if (!map.containsKey(pattern[i]) && !reverseMap.containsKey(words[i])) {
        map.put(pattern[i], words[i]);
        reverseMap.put(words[i], pattern[i]);
        continue;
    }
    if (map.containsKey(pattern[i]) != reverseMap.containsKey(words[i])) return false;
    if (!map.get(pattern[i]).equals(words[i])) return false;
}
```

- **장점**: O(n) — containsKey는 O(1)
- **단점**: 한쪽 Map에만 키가 있는 경우 NPE 방지 로직 필요, 조건문 3개로 증가
- **판단**: 성능 이득 대비 복잡도 증가. 입력이 작으면 오버엔지니어링

### v3. put 반환값 활용 (간결, O(n))

```java
Map<Object, Integer> map = new HashMap<>();
for (int i = 0; i < words.length; i++) {
    if (!Objects.equals(map.put(pattern.charAt(i), i), map.put(words[i], i))) {
        return false;
    }
}
```

- **원리**: `put()`은 이전 값을 반환 (없으면 null). 1:1 대응이면 같은 시점에 등장했으므로 이전 인덱스가 항상 같아야 함
- **장점**: 코드 3줄, Map 하나, O(n)
- **단점**: `Map<Object, Integer>`로 타입 안전성 포기, 처음 보면 의도 파악이 어려움

---

## 트레이드오프 정리

| 기준 | v1 containsValue | v2 양방향 Map | v3 put 반환값 |
|------|-------------------|---------------|---------------|
| 시간복잡도 | O(n²) | O(n) | O(n) |
| 코드 가독성 | ⭐⭐⭐ | ⭐ | ⭐⭐ |
| 조건문 수 | 2개 | 3개 | 1개 |
| 타입 안전성 | ✅ | ✅ | ❌ (Object) |
| 코테 추천 | ✅ 입력 작을 때 | △ | △ 트릭 숙지 시 |

---

## 배운 점

1. **"최적화할 수 있다" ≠ "최적화해야 한다"** — 입력 크기가 작으면 가독성이 우선
2. **예외를 흐름 제어로 쓰지 말 것** — try-catch 대신 사전 조건 검증
3. **containsValue는 O(n)** — Map의 value 검색은 key 검색과 다름
4. **put() 반환값** — 이전 값을 반환하는 특성을 활용한 패턴 존재
