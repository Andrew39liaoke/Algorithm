package queue;

import java.util.LinkedList;

// 和至少为K的最短子数组
// 测试链接 : https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/

/*
* 开始自己写的思路
* 将本题归为了滑动窗口的问题，因此采用了单调队列的思路进行解决
* 但是漏考虑了一点，滑动窗口无法解决数组中有负数的情况
* */
public class ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        ShortestSubarrayWithSumAtLeastK shortestSubarrayWithSumAtLeastK = new ShortestSubarrayWithSumAtLeastK();
        shortestSubarrayWithSumAtLeastK.shortestSubarray(new int[]{1},1);
    }
    public int shortestSubarray(int[] nums, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            queue.add(i);
            sum += nums[i];
            while (!queue.isEmpty() && sum >= k){
                ans = Math.min(ans,queue.size());
                Integer l = queue.removeFirst();
                sum -= nums[l];
            }
        }
        while (!queue.isEmpty()){
            Integer l = queue.removeFirst();
            sum -= nums[l];
            if (sum >= k) ans = Math.min(ans,queue.size());
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }



    /*
     * 采用前缀和 + 单调队列的思路进行解决
     * 为什么可行，为什么单调队列维持递增的前缀和实现
     * 代码中的关键思路是首先构建一个前缀和
     * 然后遍历右边界i，找到靠近左侧符合条件的最右边一个
     * 因此如果i放到队列中去，队列中比sum[i]大的数应该移出，因为他已经没有成为最小值的情况了
     * */
    public static int MAXN = 100001;

    // sum[0] : 前0个数的前缀和
    // sum[i] : 前i个数的前缀和
    public static long[] sum = new long[MAXN];

    public static int[] deque = new int[MAXN];

    public static int h, t;

    public static int shortestSubarray2(int[] arr, int K) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // [3,4,5]
            //  0 1 2
            // sum[0] = 0
            // sum[1] = 3
            // sum[2] = 7
            // sum[3] = 12
            sum[i + 1] = sum[i] + arr[i];
        }
        h = t = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            // 前0个数前缀和
            // 前1个数前缀和
            // 前2个数前缀和
            // ...
            // 前n个数前缀和
            while (h != t && sum[i] - sum[deque[h]] >= K) {
                // 如果当前的前缀和 - 头前缀和，达标！
                ans = Math.min(ans, i - deque[h++]);
            }
            // 前i个数前缀和，从尾部加入
            // 小 大
            while (h != t && sum[deque[t - 1]] >= sum[i]) {
                t--;
            }
            deque[t++] = i;
        }
        return ans != Integer.MAX_VALUE ? ans : -1;
    }
}

