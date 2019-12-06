/**
 * Created by rupalph on 11/1/19.
 */
public class LargestRectangleHistogram {

    static int largestRect(int height[]){
        int l = 0;
        int r = 0;

        // min height from l to r
        // minheight*(r-l+1)

        // for len 1 to n, largestRect(height, l, l+len)
        int[][] dp = new int[height.length+1][height.length+1];
        dp[0][0] = 0;
        for(int i=1;i<=height.length;i++){
            dp[i][i] = height[i-1];
        }
        int max  = 0;
        for(int i=1;i<=height.length;i++) {
            for (int j = i + 1; j <= height.length; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], height[j]);
                max = Math.max(dp[i][j] * (j - i + 1), max);
            }
        }
        return max;
    }
}
