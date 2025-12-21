package queue;

import java.util.LinkedList;

// 满足不等式的最大值
// 测试链接 : https://leetcode.cn/problems/max-value-of-equation/
/*
* 没有一点思路
* 查看左神代码思路感悟：
* 总是需要维护一个变一个不变的
* 维持一个j为右准备进入点进行统计
* */
public class MaxValueOfEquation {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int ans = Integer.MIN_VALUE;
        LinkedList<Integer[]> queue = new LinkedList<>();
        for (int i = 0; i < points.length; i++) {
            int xj = points[i][0];
            int yj = points[i][1];
            while (!queue.isEmpty() && xj - k > queue.getFirst()[0]){
                queue.removeFirst();
            }
            if(!queue.isEmpty()){
                ans = Math.max(ans,yj  + queue.getFirst()[1] + xj - queue.getFirst()[0]);
            }
            while (!queue.isEmpty() && queue.getLast()[1] - queue.getLast()[0] <= yj - xj){
                queue.removeLast();
            }
            queue.addLast(new Integer[]{xj,yj});
        }
        return ans;
    }
}
