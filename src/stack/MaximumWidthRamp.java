package stack;

// 最大宽度坡
// 测试链接 : https://leetcode.cn/problems/maximum-width-ramp/
/*
 * 开始思路：怎么实现单调栈实现，比cur大右侧最远的index
 * 思路错误
 * 左神思路：维持一个小压大的单调栈，比我数字大的，不需要进栈，因为他构成的坡度不可能比栈顶构成的坡度大
 * 疑问：什么时候进行结果的统计？
 * 维持一个小压大的栈，再从最优侧开始遍历，cur 大于等于栈顶则出栈，记录结果
 * */
public class MaximumWidthRamp {
    public static int MAXN = 100001;
    public static int[] stack = new int[MAXN];
    public static int r;

    public int maxWidthRamp(int[] nums) {
        int ans = 0;
        r = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[stack[r - 1]]) {
                stack[r++] = i;
            }
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            while (r > 0 && nums[i] >= nums[stack[r -1]]){
                int left = stack[--r];
                ans = Math.max(ans,i - left);
            }
        }
        return ans;
    }
}
