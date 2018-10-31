import java.util.*;
import java.util.stream.Collectors;

/**
 * 126. Word Ladder II
 * Created by man on 2018/10/31.
 */
public class Solution126 {
    /*
      描述:
      Given two words (beginWord and endWord), and a dictionary's word list,
      find all shortest transformation sequence(s) from beginWord to endWord, such that:
          1.Only one letter can be changed at a time
          2.Each transformed word must exist in the word list.
            Note that beginWord is not a transformed word.

      Note:
          + Return an empty list if there is no such transformation sequence.
          + All words have the same length.
          + All words contain only lowercase alphabetic characters.
          + You may assume no duplicates in the word list.
          + You may assume beginWord and endWord are non-empty and are not the same.
     */

    /*
      case1:
      Input:
      beginWord = "hit",
      endWord = "cog",
      wordList = ["hot","dot","dog","lot","log","cog"]

      Output:
      [
        ["hit","hot","dot","dog","cog"],
        ["hit","hot","lot","log","cog"]
      ]

      case2:
      Input:
      beginWord = "hit"
      endWord = "cog"
      wordList = ["hot","dot","dog","lot","log"]

      Output: []

      Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
     */

    /*
      不能通过测试用例
      "red"
      "tax"
      ["ted","tex","red","tax","tad","den","rex","pee"]

      我的输出[["red","ted","tex","tax"],["red","ted","tad","tax"]]
      正确输出[["red","ted","tad","tax"],["red","ted","tex","tax"],["red","rex","tex","tax"]]
     */
    // 此方法超时
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 使用宽度优先搜索最优解
        Queue<String> queueWords = new LinkedList<>();
        Queue<List<String>> queueLists = new LinkedList<>();
        queueLists.offer(new LinkedList<>());
        queueLists.peek().add(beginWord);
        queueWords.offer(beginWord);

        List<List<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        while (!queueWords.isEmpty()) {
            int size = queueWords.size();
            boolean hasAnswer = !result.isEmpty();  // 该轮次是否已经出现最优解了
            Set<String> words = new HashSet<>();  // 记录此轮添加的候选词
            for (int i = 0; i < size; i++) {
                String curWord = queueWords.poll();
                List<String> path = queueLists.poll();
                if (!hasAnswer) {  // 已经出现最优解，则没必要继续添加可能解
                    List<String> transformedWords = transformedWord(curWord, wordList);
                    transformedWords.forEach(word -> {
                        if (!visited.contains(word)) {
                            List<String> nextPath = new LinkedList<>(path);
                            nextPath.add(word);
                            if (word.equals(endWord)) {
                                result.add(nextPath);
                            } else {  // 如果不是目标词，则继续添加可能性
                                queueWords.add(word);
                                queueLists.add(nextPath);
                                words.add(word);
                            }
                        }
                    });
                }
            }
            visited.addAll(words);
        }

        return result;
    }

    // 根据当前词得到下一个可能的词
    public List<String> transformedWord(String curWord, List<String> wordList) {
        return wordList.stream()
                .filter(word -> {
                    int cnt = 0;
                    for (int i = 0; i < curWord.length(); i++) {
                        cnt += curWord.charAt(i) == word.charAt(i) ? 0 : 1;
                        if (cnt > 1) return false;
                    }
                    return cnt == 1;
                }).collect(Collectors.toList());
    }

    // source:https://leetcode.com/problems/word-ladder-ii/discuss/40434/C++-solution-using-standard-BFS-method-no-DFS-or-backtracking
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        Queue<List<String>> queue = new LinkedList<>();  // 仅保存路径有可以了
        queue.offer(new LinkedList<>());
        queue.peek().add(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        List<List<String>> result = new ArrayList<>();

        while (!queue.isEmpty() && result.isEmpty()) {
            // 一轮操作
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                List<String> path = queue.poll();
                String curWord = path.get(path.size() - 1);
                List<String> transformedWords = transformedWord(curWord, wordList);
                transformedWords.forEach(word -> {
                    List<String> nextPath = new LinkedList<>(path);
                    nextPath.add(word);
                    if (word.equals(endWord)) {
                        result.add(nextPath);
                    } else {
                        queue.offer(nextPath);
                        visited.add(word);
                    }
                });
            }

            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // 去除每一轮访问完成后的word
            // 通过这样去除，每次减小候选单词表的大小
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            wordList.removeAll(visited);
            visited.clear();
        }

        return result;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new LinkedList<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        System.out.println(new Solution126().findLadders2(beginWord, endWord, wordList));
    }
}
