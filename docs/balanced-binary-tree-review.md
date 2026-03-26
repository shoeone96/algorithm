# Balanced Binary Tree - height-balanced 판별

> **문제**: [LC 110. Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/)
> **유형**: 트리 DFS (반환값 + 전역 갱신 동시 사용)
> **코드**: `src/y2026/mar/week4/BalancedBinaryTree.java`

---

## 문제 핵심

**모든 노드**에서 왼쪽/오른쪽 서브트리의 높이 차이가 **1 이하**인지 판별.
완전이진트리가 아니어도 balanced일 수 있다.

```
      1        ← 왼쪽 높이 2, 오른쪽 높이 1, 차이 1 → OK
     / \
    2   3
   /
  4
```

---

## 풀이

```java
boolean answer = true;

public boolean isBalanced(TreeNode root) {
    dfs(root);
    return answer;
}

private int dfs(TreeNode root) {
    if (root == null) return 0;
    int leftDepth = dfs(root.left);
    int rightDepth = dfs(root.right);
    if (Math.abs(leftDepth - rightDepth) > 1) {
        answer = false;
    }
    return Integer.max(leftDepth, rightDepth) + 1;
}
```

### DFS의 두 가지 역할

| 역할 | 코드 | 설명 |
|------|------|------|
| **balanced 판별** | `if (abs(left - right) > 1) answer = false` | 이 노드에서 균형이 깨졌는지 확인 |
| **높이 반환** | `return max(left, right) + 1` | 부모에게 서브트리 높이를 올려보냄 |

---

## 헷갈렸던 부분: "반환값 = 정답"이 아니다

### 기존에 익숙했던 방식

DFS의 return값이 곧 정답. 예: Maximum Depth (LC 104)에서 `return max(left, right) + 1`이 그대로 답.

### 오늘 배운 패턴 (Diameter + Balanced 공통)

DFS의 return값은 **높이(도구)**이고, 진짜 정답은 **중간에 따로 갱신**한다.

```
return값    → 부모에게 올려보내는 재료 (높이)
answer 갱신 → 그 재료를 조합해서 진짜 답을 구하는 과정
```

- **Diameter**: `answer = max(answer, left + right)` → 양쪽 높이를 합쳐서 지름 후보
- **Balanced**: `if (abs(left - right) > 1) answer = false` → 양쪽 높이를 비교해서 균형 판별

두 문제 모두 DFS 메서드의 **목적은 높이를 구하는 것**이고, 정답은 그 높이를 활용한 **추가 조건문**에서 나온다.

---

## 패턴 키워드

- **"반환값 + 전역 갱신" 분리 패턴**: return은 도구, 정답은 중간에 갱신
- **Bottom-up DFS**: 리프에서 루트로 높이를 올려받는 방식
- **관련 문제**: LC 543 Diameter, LC 124 Binary Tree Maximum Path Sum
