package stack;

import java.util.Arrays;

// 去除重复字母保证剩余字符串的字典序最小
// 测试链接 : https://leetcode.cn/problems/remove-duplicate-letters/
/*
 * 第一次代码：未考虑到重复进栈的情况
 * 第二次代码：使用静态变量，每次执行代码前，未进行重新初始化
 * 第三次代码：相同，未进栈后续的数量也应该变化，charNums[chars[i] - 'a']--;位置不应该放if里面
 * 第四次代码：逻辑顺序颠倒，把 “跳过” 放在 “破坏操作之后”,先判断是否进栈，再进行栈中元素调整
 * */
public class RemoveDuplicateLetters {
    public static int MAXN = 100001;
    public static char[] stack = new char[MAXN];
    public static int[] charNums = new int[26];
    public static int r;

    /*添加进栈元素统计
     * */
    public static int[] push = new int[26];

    public String removeDuplicateLetters(String s) {
        r = 0;
        Arrays.fill(charNums, 0);
        Arrays.fill(push, 0);
        char[] chars = s.toCharArray();
        for (char c : chars) {
            charNums[c - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            charNums[chars[i] - 'a']--;
            if (push[chars[i] - 'a'] == 1) continue;
            while (r > 0 && stack[r - 1] > chars[i] && charNums[stack[r - 1] - 'a'] > 0) {
                push[stack[r - 1] - 'a']--;
                r--;
            }
            stack[r++] = chars[i];
            push[chars[i] - 'a'] = 1;
        }
        return String.valueOf(stack, 0, r);
    }
}
