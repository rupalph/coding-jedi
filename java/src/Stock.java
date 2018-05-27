import java.util.Arrays;

/**
 * Created by rupalh on 5/21/18.
 */
public class Stock {
    public void maxProfit(int[] prices, int k){

        int[][] dp = new int[k+1][prices.length];
        for(int i =1; i<=k; i++){
            int maxDiff = Integer.MIN_VALUE;
            for(int j=1;j<prices.length;j++){
                //buy today (prices[j]) - sell yesterday (prices[j-1]) + prev trans. (dp[i-1][j-1]
                maxDiff = Math.max(maxDiff, (dp[i-1][j-1]-prices[j-1]));
                dp[i][j] = Math.max(dp[i][j-1],maxDiff+prices[j]);
            }
        }

        System.out.println(Arrays.deepToString(dp));

        printSteps(dp,prices);
    }

    public void printSteps(int[][] dp, int[] prices){
        StringBuffer sb = new StringBuffer();
        int i=dp.length-1;
        int j=dp[0].length-1;

        for(int k=0;k<dp[0].length;k++) {
            while(i>=0 && j-1>=0 && dp[i][j] == dp[i][j-1])
                j--;
            if(j>=0 && i>=0 && dp[i][j]==0)
                break;

            sb.insert(0,"\nsell at "+prices[j]+" on day #" + j);
            int diff = dp[i][j]-prices[j];
            i--;j--;
            while(i>=0 && j>=0 && dp[i][j]-prices[j]!=diff)
                j--;
            if(j>=0)
                sb.insert(0,"\nbuy at "+prices[j]+" on day #" + j);
        }

        System.out.println(sb.toString());

    }

    public static void main(String[] args){
        Stock s = new Stock();
        s.maxProfit(new int[]{3,2,6,9,2},3);
        s.maxProfit(new int[]{2,5,5,7,1,4,3,1,3},3);

    }
}
