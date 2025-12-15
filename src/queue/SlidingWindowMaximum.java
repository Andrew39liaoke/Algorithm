package queue;

import java.util.LinkedList;

// 滑动窗口最大值（单调队列经典用法模版）
// 测试链接 : https://leetcode.cn/problems/sliding-window-maximum/
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        int n = nums.length;
        int m = n - k + 1;
        int[] ans = new int[m];
        /*
        * 先形成一个k大小的窗口先 (比较妙)
        * */
        for (int i = 0; i < k - 1;i++){
            while (!list.isEmpty() && nums[list.getLast()] <= nums[i]){
                list.removeLast();
            }
            list.addLast(i);
        }

        for (int l = 0,r = k - 1; l < m;l++,r++){
            while (!list.isEmpty() && nums[list.getLast()] <= nums[r]){
                list.removeLast();
            }
            list.addLast(r);
            ans[l] = nums[list.getFirst()];
            if(list.getFirst() == l) list.removeFirst();
        }
        return ans;
    }
}
