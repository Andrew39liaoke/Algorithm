package stack;

import java.util.Arrays;

// 最大矩形
// 测试链接：https://leetcode.cn/problems/maximal-rectangle/
public class MaximalRectangle {
    public static int MAXN = 201;

    public static int[] height = new int[MAXN];

    public static int[] stack = new int[MAXN];

    public static int r;

    public int maximalRectangle(char[][] matrix) {
        r = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int ans = 0;
        Arrays.fill(height, 0, col, 0);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            ans = Math.max(ans, largestRectangleArea(col));
        }
        return ans;
    }

    public static int largestRectangleArea(int n) {
        int max = 0;
        r = 0;
        for (int i = 0; i < n; i++) {
            while (r > 0 && height[stack[r - 1]] >= height[i]) {
                int cur = stack[--r];
                int left = r == 0 ? -1 : stack[r - 1];
                max = Math.max(max, (i - left - 1) * height[cur]);
            }
            stack[r++] = i;
        }

        /*
         * 存留在栈中的代表cur元素右侧没有比我更小的数字了
         * */
        while (r > 0) {
            int cur = stack[--r];
            int left = r == 0 ? -1 : stack[r - 1];
            max = Math.max(max, (n - left - 1) * height[cur]);
        }
        return max;
    }
}
