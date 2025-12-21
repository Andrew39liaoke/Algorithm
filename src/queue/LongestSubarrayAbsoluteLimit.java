package queue;
// 绝对差不超过限制的最长连续子数组
// 测试链接 : https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
/*
* 思路不清晰:
* 关键性思路：
* 往右侧扩，范围变大了，那么最大值只有可能更大或者不变，最小值只有可能更小或者不变
* 左边界移动，范围变小，那么最大值只有可能更小或者不变，最小值只有可能更大或者不变
* 因此维护两个单调队列，记录窗口大小下的最大值单调队列以及最小值的单调队列
*
* */


import java.util.LinkedList;

public class LongestSubarrayAbsoluteLimit {
    public int longestSubarray(int[] nums, int limit) {
        LinkedList<Integer> maxList = new LinkedList<>();
        LinkedList<Integer> minList = new LinkedList<>();
        int ans = 0;
        /*
        * r永远问的是能不能进来的位置
        * l才是左侧的边界
        * */
        for (int l = 0,r = 0;l < nums.length;l++){
            /*
            * 判断时候，先扩右侧，判断是否符合
            * 不符合，进行左侧收缩
            * 符合，则进行扩
            * */
            while (r < nums.length && isOK(maxList,minList,limit,nums[r],nums)){
                while (!maxList.isEmpty() && nums[maxList.getLast()] <= nums[r]){
                    maxList.removeLast();
                }
                maxList.addLast(r);
                while (!minList.isEmpty() && nums[minList.getLast()] >= nums[r]){
                    minList.removeLast();
                }
                minList.addLast(r++);
            }
            ans = Math.max(ans,r - l);
            while (!maxList.isEmpty() && maxList.getFirst() == l){
                maxList.removeFirst();
            }
            while (!minList.isEmpty() && minList.getFirst() == l){
                minList.removeFirst();
            }
        }
        return ans;
    }
    public boolean isOK(LinkedList<Integer> maxList, LinkedList<Integer> minList, int limit,int number,int[] nums){
        int max = maxList.isEmpty() ? number : Math.max(number,nums[maxList.getFirst()]);
        int min = minList.isEmpty() ? number : Math.min(number,nums[minList.getFirst()]);
        return max - min <= limit;
    }
}
