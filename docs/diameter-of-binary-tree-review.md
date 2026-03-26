# Diameter of Binary Tree - 트리 지름 패턴

> **문제**: [LC 543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/)
> **유형**: 트리 DFS (반환값 + 전역 갱신 동시 사용)
> **코드**: `src/y2026/mar/week4/DiameterOfBinaryTree.java`

---

## 문제 핵심

트리에서 **가장 먼 두 노드 사이의 경로 길이(간선 수)**를 구하는 문제.
트리에서 두 노드 사이의 경로는 하나뿐이므로, "가장 먼 두 노드를 잇는 유일한 경로"가 지름이다.

---

## 핵심 발상: 모든 노드를 "꼭대기"로 본다

두 노드 사이의 경로는 항상 **뒤집힌 V자** 모양이다:

```
      꼭대기
     ↗     ↖
   ...      ...
  /            \
노드A         노드B
```

- 꼭대기가 **루트일 수도 있고 아닐 수도 있다**
- 따라서 **모든 노드를 꼭대기로 삼아서** 왼쪽 깊이 + 오른쪽 깊이를 구해봐야 한다
- 그 중 최댓값이 지름

---

## 풀이: DFS 한 번에 두 가지 역할

```java
int answer = 0;

public int diameterOfBinaryTree(TreeNode root) {
    if (root == null) return 0;
    dfs(root);
    return answer;
}

private int dfs(TreeNode root) {
    if (root == null) return 0;
    int leftSum = dfs(root.left);
    int rightSum = dfs(root.right);
    answer = Integer.max(answer, leftSum + rightSum);  // 지름 후보 갱신
    return Math.max(leftSum, rightSum) + 1;             // 부모에게 높이 반환
}
```

### 하나의 DFS가 하는 두 가지 일

| 역할 | 코드 | 설명 |
|------|------|------|
| **지름 갱신** | `answer = max(answer, leftSum + rightSum)` | 이 노드가 꼭대기일 때의 지름 후보 (양쪽 합) |
| **높이 반환** | `return max(leftSum, rightSum) + 1` | 부모에게 올려보내는 값 (한쪽 길만) |

### 왜 return은 한쪽만?

부모 노드는 나를 **한쪽 자식**으로 보고 있다. 부모가 나를 통해 내려갈 때, 내 왼쪽/오른쪽 중 **하나만 선택** 가능하다 (경로가 갈라지면 안 되니까). `+1`은 나와 부모 사이의 간선 하나.

```
    부모
     \
      나
     / \
    3   5   ← 부모는 더 긴 5쪽 길만 쓸 수 있음
              return 5 + 1 = 6
```

양쪽을 합치는 건 **"나"가 꼭대기일 때만** 의미 있고, 그건 `answer` 갱신에서 이미 처리했다.

---

## 접근법 정리: 이런 문제를 만났을 때

1. **"지름" = 가장 먼 두 노드 사이 거리**. 그래프 이론 용어.
2. 경로는 반드시 어떤 노드를 **꼭대기로 한 뒤집힌 V자**. 루트를 반드시 지나지 않는다.
3. 모든 노드에서 **왼쪽 서브트리 높이 + 오른쪽 서브트리 높이**를 구해서 최댓값 추적.
4. LC 104 Maximum Depth의 Bottom-up DFS를 재활용하되, **반환값(높이)과 전역 갱신(지름)**을 분리한다.

### 만약 루트를 반드시 지나야 한다면?

전역 변수 없이 루트에서의 `leftSum + rightSum`이 바로 답이 된다.

---

## 패턴 키워드

- **"반환값 + 전역 갱신" 동시 사용 패턴**: 트리에서 자주 등장 (LC 124 Binary Tree Maximum Path Sum 등)
- **Bottom-up DFS**: 리프에서 루트로 정보를 올려받는 방식
