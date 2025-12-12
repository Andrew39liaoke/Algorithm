package stack;
// 大鱼吃小鱼问题
// 测试链接 : https://www.nowcoder.com/practice/77199defc4b74b24b8ebf6244e1793de
// 测试链接 : https://leetcode.cn/problems/steps-to-make-array-non-decreasing/
/*
* 无法和单调栈进行联系
*
* 左神思路：
* 每次都是同步吃掉右侧比我小的数字
* 将整体思维转为局部，右侧开始记录吃掉右侧所有所需要的轮数
* 维持一个单调栈记录，出栈代表吃掉，轮数加1
* */
public class BigFishEatSmallFish {
    public static int MAXN = 100001;
    public static int[][] stack = new int[MAXN][2];
    public static int r;
    public int totalSteps(int[] nums) {
        int ans = 0,num = 0;
        r = 0;
        for (int i = nums.length - 1; i >= 0;i--){
            num = nums[i];
            int step = 0;
            while (r > 0 && num > stack[r - 1][0]){
                int temp = stack[--r][1];
                step = Math.max(step + 1,temp);
            }
            stack[r][0] = num;
            stack[r++][1] = step;
            ans = Math.max(step,ans);
        }
        return ans;
    }
}
