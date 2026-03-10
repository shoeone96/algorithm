# 3Sum - 풀이 기록

## 문제
배열에서 세 수의 합이 0이 되는 모든 고유한 triplet을 찾는 문제입니다.

---

## 첫 번째 시도 (시간초과)

```java
public List<List<Integer>> threeSum(int[] nums) {
    Set<List<Integer>> answer = new HashSet<>();
    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i++) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (left == i) {
                left++;
                continue;
            } else if (right == i) {
                right--;
                continue;
            }

            int temp = nums[left] + nums[right] + nums[i];

            if (temp < 0) {
                left++;
            } else if (temp > 0) {
                right--;
            } else {
                List<Integer> list = Stream.of(nums[left], nums[right], nums[i]).sorted().toList();
                answer.add(list);
                left++;
            }
        }
    }
    return answer.stream().toList();
}
```

### 시간초과 원인
1. 매번 `left=0`, `right=n-1`로 전체 범위 탐색
2. i를 건너뛰는 조건문 오버헤드
3. `Stream.of().sorted().toList()` 매번 호출

---

## 두 번째 시도 (통과)

### 변경 1: 루프 범위 최적화
```java
// before
for (int i = 0; i < nums.length; i++)

// after
for (int i = 0; i < nums.length - 2; i++)
```

### 변경 2: 중복 i 건너뛰기 추가
```java
// before
// 없음

// after
if (i > 0 && nums[i] == nums[i - 1]) {
    continue;
}
```

### 변경 3: 탐색 시작점 변경
```java
// before
int left = 0;
int right = nums.length - 1;
while (left < right) {
    if (left == i) {
        left++;
        continue;
    } else if (right == i) {
        right--;
        continue;
    }
    // ...
}

// after
int left = i + 1;
int right = nums.length - 1;
while (left < right) {
    // i를 건너뛸 필요 없음
    // ...
}
```

### 변경 4: 리스트 생성 방식
```java
// before
List<Integer> list = Stream.of(nums[left], nums[right], nums[i]).sorted().toList();

// after (이미 정렬되어 있으므로 순서대로)
List<Integer> list = List.of(nums[i], nums[left], nums[right]);
```

---

## 세 번째 시도 (최적화)

### 변경 5: HashSet → ArrayList
```java
// before
Set<List<Integer>> answer = new HashSet<>();
// ...
return answer.stream().toList();

// after (중복을 직접 처리하므로 Set 불필요)
List<List<Integer>> answer = new ArrayList<>();
// ...
return answer;
```

### 변경 6: 중복 left/right 건너뛰기 추가
```java
// before
} else {
    List<Integer> list = List.of(nums[i], nums[left], nums[right]);
    answer.add(list);
    left++;
}

// after
} else {
    List<Integer> list = List.of(nums[i], nums[left], nums[right]);
    answer.add(list);
    // 중복 left 건너뛰기
    while (left < right && nums[left] == nums[left + 1]) {
        left++;
    }
    // 중복 right 건너뛰기
    while (left < right && nums[right] == nums[right - 1]) {
        right--;
    }
    left++;
    right--;  // 양쪽 모두 이동
}
```

### 최종 코드 (Solution2.java)
```java
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> answer = new ArrayList<>();
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 2; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue;
        }
        int left = i + 1;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[i] + nums[left] + nums[right];
            if (temp < 0) {
                left++;
            } else if (temp > 0) {
                right--;
            } else {
                List<Integer> list = List.of(nums[i], nums[left], nums[right]);
                answer.add(list);
                while (left < right && nums[left] == nums[left + 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right - 1]) {
                    right--;
                }
                left++;
                right--;
            }
        }
    }
    return answer;
}
```

---

## 핵심 포인트

- `left = i + 1`부터 시작하면 i를 건너뛰는 조건문이 불필요
- 배열이 정렬되어 있으므로 `nums[i] <= nums[left] <= nums[right]` 보장
- 중복 i, left, right 모두 건너뛰면 HashSet 없이 ArrayList로 처리 가능
- 정답 찾은 후 양쪽 포인터 모두 이동 (`left++`, `right--`)

## 시간/공간 복잡도

- **시간 복잡도**: O(n²)
- **공간 복잡도**: O(1) - 결과 제외 (HashSet 제거로 개선)
