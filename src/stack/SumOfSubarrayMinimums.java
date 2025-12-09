package stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 子数组的最小值之和
// 测试链接 : https://leetcode.cn/problems/sum-of-subarray-minimums/
/*
* 自己思路：
* 分析以当前这个index数字为最小的组合数有几个
* 进而转化成，又左/右可移动多少找到比index小的数字
* 难点：要是碰到相同的数字应该怎么处理
* 避免重复计算，采取非对称式
* 然后统计出当先为最小值的开头可能性和结尾可能性
* */

/*
* 左神思路：
*
* */
public class SumOfSubarrayMinimums {
    public int sumSubarrayMins(int[] arr) {
        Deque<Integer> lt = new LinkedList<>();
        Deque<Integer> rt = new LinkedList<>();
        int length = arr.length;
        int[] l = new int[length];
        Arrays.fill(l,-1);
        int[] r = new int[length];
        Arrays.fill(r,length);
        for (int i = 0; i < arr.length; i++) {
            while (!rt.isEmpty() && arr[i] < arr[rt.getLast()]){
                int index = rt.removeLast();
                r[index] = i;
            }
            rt.addLast(i);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!lt.isEmpty() && arr[i] <= arr[lt.getLast()]){
                int index = lt.removeLast();
                l[index] = i;
            }
            lt.addLast(i);
        }
        long ans = 0;
        int MOD = 1000000007;
        for (int i = 0; i < length; i++) {
            long count = (long) (i - l[i]) * (r[i] - i); // 子数组个数
            ans = (ans + count * arr[i]) % MOD; // 累加贡献，取模防溢出
        }
        return (int) ans;
    }

    public static int MOD = 1000000007;

    public static int MAXN = 30001;

    public static int[] stack = new int[MAXN];

    public static int r;

    public static int sumSubarrayMins2(int[] arr) {
        int length = arr.length;
        long ans = 0;
        r  = 0;
        for (int i = 0; i < arr.length; i++) {
            while (r > 0 && arr[i] > arr[stack[r - 1]]){
                int cur = stack[--r];
                int left = r == 0 ? -1:stack[r - 1];
                ans = (ans + (long) (cur - left) * (i - cur) * arr[cur]) % MOD;
            }
            stack[r++] = i;
        }
        while (r > 0){
            int cur = stack[--r];
            int left = r == 0 ? -1:stack[r-1];
            ans = (ans + (long) (cur - left) * (length - cur) * arr[cur]) % MOD;
        }
        return (int) ans;
    }
}
