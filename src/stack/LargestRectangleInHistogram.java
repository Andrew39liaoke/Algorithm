package stack;

// 柱状图中最大的矩形
// 测试链接：https://leetcode.cn/problems/largest-rectangle-in-histogram
public class LargestRectangleInHistogram {
    public static int MAXN = 100001;

    public static int[] stack = new int[MAXN];

    public static int r;
    public static int largestRectangleArea(int[] height) {
        int max = 0;
        r = 0;
        for (int i = 0; i < height.length; i++) {
            while (r > 0 && height[stack[r - 1]] >= height[i]){
                int cur = stack[--r];
                int left = r == 0 ? -1 : stack[r -1];
                max = Math.max(max,(i - left - 1) * height[cur]);
            }
            stack[r++] = i;
        }

        /*
        * 存留在栈中的代表cur元素右侧没有比我更小的数字了
        * */
        while (r > 0){
            int cur = stack[--r];
            int left = r == 0 ? -1 : stack[r -1];
            max = Math.max(max,(height.length - left - 1) * height[cur]);
        }
        return max;
    }
}
