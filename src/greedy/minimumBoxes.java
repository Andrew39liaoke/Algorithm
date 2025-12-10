package greedy;

import java.util.Arrays;

//重新分装苹果
//https://leetcode.cn/problems/apple-redistribution-into-boxes/description/
public class minimumBoxes {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int total = Arrays.stream(apple).sum();
        Arrays.sort(capacity);
        int ans = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            if (total > 0){
                total -= capacity[i];
                ans++;
            }else {
                return ans;
            }
        }
        return ans;
    }
}
