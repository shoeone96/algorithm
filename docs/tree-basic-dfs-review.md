# Tree 기초 DFS - Maximum Depth + Invert Binary Tree

> **문제**: [LC 104. Maximum Depth](https://leetcode.com/problems/maximum-depth-of-binary-tree/) + [LC 226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/)
> **유형**: 트리 DFS 기초 (재귀)
> **코드**: `src/y2026/mar/week4/MaximumDepthOfBinaryTree.java`, `InvertBinaryTree.java`

---

## LC 104. Maximum Depth of Binary Tree

### 풀이 1: Top-down DFS (depth를 위에서 내려보내기)

```java
static int answer;
public int maxDepth(TreeNode root) {
    answer = 0;
    if (root != null) dfs(root, 1);
    return answer;
}
private void dfs(TreeNode node, int depth) {
    if (depth > answer) answer = depth;
    if (node.left != null) dfs(node.left, depth + 1);
    if (node.right != null) dfs(node.right, depth + 1);
}
```

- 직관적: 루트부터 내려가면서 depth를 1씩 증가
- 단점: `static` 변수 → 스레드 안전하지 않음

### 풀이 2: 반환값 방식 (static 제거)

인스턴스 변수 대신 answer를 파라미터+반환값으로 전달.
static 문제는 해결했지만, depth와 answer를 둘 다 끌고 다녀서 복잡.

### 핵심 패턴: Bottom-up DFS (아래에서 올려받기)

```
null이면 0, 아니면 1 + max(왼쪽 깊이, 오른쪽 깊이)
```

- depth를 **위에서 내려보내는** 게 아니라 **아래에서 올려받는** 발상
- 전역 상태 없이 반환값만으로 계산
- **LC 543 Diameter, LC 110 Balanced에서 이 패턴이 핵심**

---

## LC 226. Invert Binary Tree

### 풀이: DFS + swap

```java
private void dfs(TreeNode node) {
    if (node == null) return;
    TreeNode left = node.left;
    TreeNode right = node.right;
    node.right = left;
    node.left = right;
    dfs(left);
    dfs(right);
}
```

- 각 노드에서 left/right를 swap 후 재귀
- swap 먼저 → 재귀 (preorder), 또는 재귀 먼저 → swap (postorder) 둘 다 가능
- 핵심: **swap 후 재귀 대상이 바뀌지 않도록** 변수에 미리 저장

### 개선 과정

초기: `temp` 변수 불필요하게 사용 (right와 동일)
수정: `left`, `right` 두 변수만으로 깔끔하게 swap

---

## 트리 DFS 두 가지 방향

| 방향 | 설명 | 예시 |
|------|------|------|
| Top-down | 루트→리프, 정보를 내려보냄 | depth 파라미터로 전달 |
| Bottom-up | 리프→루트, 정보를 올려받음 | 반환값으로 depth 계산 |

**Bottom-up이 트리 문제의 핵심 패턴.** Diameter, Balanced, Longest Path 등에서 필수.
