import java.util.*;

/**
 * 621. Task Scheduler
 * Created by man on 2018/10/31.
 */
public class Solution621 {
    /*
    描述:
    Given a char array representing tasks CPU need to do.
    It contains capital letters A to Z where different letters represent
    different tasks.Tasks could be done without original order. Each task
    could be done in one interval. For each interval, CPU could finish one
    task or just be idle.

    However, there is a non-negative cooling interval n that means between
    two same tasks, there must be at least n intervals that CPU are doing
    different tasks or just be idle.

    You need to return the least number of intervals the CPU will take to
    finish all the given tasks.

    测试用例:
    Example1
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

    Note:

    The number of tasks is in the range [1, 10000].
    The integer n is in the range [0, 100].
     */

    // 错误的答案
    // AAAA BBBB CCCC DDDD EEEE 2 错误样例
    // ABCABCABCABCDE DE DE DE 如上方法的计算结果
    // ABCDEABCDEABCDEABCDE 正确结果
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int cnt = 0;
        List<Character> remain = new ArrayList<>(map.keySet());
        Collections.sort(remain, (c1, c2) -> {
            return map.get(c2) - map.get(c1);
        });
        List<Character> notRemain = new ArrayList<>();
        // 设定一个还剩的任务和一个保存该次工作后结束的任务
        // + 剩余任务中的数量大于 n 那么表示不需要cpu间隔
        //   将这些任务的个数减1，并且减一后，如果数量为0，
        //   表示下次不在参与比较，将其放入不保留任务中。
        // + 剩余任务数量小于等于n时，表示需要cpu空闲间隔，
        //   将这些数量的任务数减1，并保存不在保留的任务。
        // + 完成一个cpu间隔后，将剩余任务中的个数为0的任务剔除。
        //   同时判定剩余任务size是否为0，若为0表示为最后一次cpu调度。
        //   否则，cnt计算加n+1。
        while (!remain.isEmpty()) {
            if (remain.size() > n) {
                for (int k = 0; k <= n; k++) {
                    int num = map.get(remain.get(k));
                    map.put(remain.get(k), num-1);
                    if (0 == num-1) notRemain.add(remain.get(k));
                }
            } else {
                for (int k = 0; k < remain.size(); k++) {
                    int num = map.get(remain.get(k));
                    map.put(remain.get(k), num-1);
                    if (0 == num-1) notRemain.add(remain.get(k));
                }
            }
            int size = remain.size();
            remain.removeAll(notRemain);
            notRemain.clear();
            if (remain.isEmpty()) {
                cnt += size;
            } else {
                cnt += n+1;
            }
        }
        return cnt;
    }

    // source: https://leetcode.com/problems/task-scheduler/discuss/104504/C++-8lines-O(n)
    public int leastInterval2(char[] tasks, int n) {
        /*
        AAAA BBBB CCCC DDDD EEEE 2
        ABCABCABCABCDE DE DE DE 如上方法的计算结果
        + 统计所有任务出现的频率，以及频率的最大值m。
        + 以频率最大的任务为准，则至少要调度m-1次个cpu区间
          保证两个相同任务之间至少间隔n.其余的任务都可以插入到这些区间中
        + 当最后一次调度时，每出现一个频率为m的任务，那么就要多一次调度
        + 将结果与至少的调度次数(length)比较，如上面展示的例子，在以A为准
          每个间隔之间插入了，BCDE，每个间隔的大小大于了2+1，因此应该选择
          数组长度的调度次数。
         */
        Map<Character, Integer> map = new HashMap<>();
        int cnt = 0;
        for (char c: tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            cnt = Math.max(map.get(c), cnt);
        }

        int res = (cnt-1) * (n+1);
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == cnt) res++;
        }

        return Math.max(res, tasks.length);
    }

    public static void main(String[] args) {
        char[] tasks = new char[] {
                'A', 'A', 'A', 'A',
                'B', 'B', 'B', 'B',
                'C', 'C', 'C', 'C',
                'D', 'D', 'D', 'D',
                'E', 'E', 'E', 'E' };
        System.out.println(new Solution621().leastInterval(tasks, 2));
    }
}
