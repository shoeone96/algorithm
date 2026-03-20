package y2026.mar.week3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class WordLadder {
    /**
     * 인접리스트가 없다면 만들어보는 게 어떨까
     * 연결이 가능한 대상들을 보고 인접리스트로 만들기
     * 양방향 인접리스트로 만드는 것
     * List 순서대로 만들면 되겠지
     * 그리고 첫 방문은 따로 만들어서(Queue에 넣고 거기는 isVisited true로 만들고 1로 시작)
     * 최단 거리 구해야 하니 거리도 같이 넣자
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int answer = 0;
        List<List<Integer>> adjacentList = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            adjacentList.add(new ArrayList<>());
        }

        for (int i = 0; i < wordList.size() - 1; i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (isValidated(wordList.get(i), wordList.get(j))) {
                    adjacentList.get(i).add(j);
                    adjacentList.get(j).add(i);
                }
            }
        }

        boolean[] isVisited = new boolean[wordList.size()];

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < wordList.size(); i++) {
            if (isValidated(beginWord, wordList.get(i))) {
                queue.offer(new int[]{i, 2});
                isVisited[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (wordList.get(poll[0]).equals(endWord)) {
                answer = poll[1];
                break;
            }

            for (int i = 0; i < adjacentList.get(poll[0]).size(); i++) {
                if (!isVisited[adjacentList.get(poll[0]).get(i)]) {
                    isVisited[adjacentList.get(poll[0]).get(i)] = true;
                    queue.offer(new int[]{adjacentList.get(poll[0]).get(i), poll[1] + 1});
                }
            }
        }

        return answer;
    }

    private boolean isValidated(String standard, String target) {
        int count = 0;
        for (int i = 0; i < standard.length(); i++) {
            if (standard.charAt(i) == target.charAt(i)) {
                count++;
            }
        }

        return count == (standard.length() - 1);
    }

}
