package stack;
// 每日温度
// 测试链接 : https://leetcode.cn/problems/daily-temperatures/

import java.util.Stack;

public class DailyTemperatures {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] ints = dailyTemperatures.dailyTemperaturesByArr(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

    // 实现方式一： 实现java自带的栈stack
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!st.isEmpty() && temperatures[i] > temperatures[st.peek()] ){
                Integer index = st.pop();
                ans[index] = i - index;
            }
            st.push(i);
        }
        return ans;
    }

    // 实现方式二： 适用数组的方式进行实现,大大加快了速度
    public int[] dailyTemperaturesByArr(int[] temperatures){
        int length = temperatures.length;
        int[] ans = new int[length];
        int[] stack = new int[length];
        int left = 0,right = 0;
        for (int i = 0; i < temperatures.length; i++) {
            while (right > 0 && temperatures[i] > temperatures[stack[right - 1]]){
                int index = stack[--right];
                ans[index] = i - index;
            }
            stack[right++] = i;
        }
        return ans;
    }
}
