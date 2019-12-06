/**
 * Created by rupalph on 10/10/19.
 */
class BuySellStock {
    public int maxProfit(int[] prices) {


//         int maxprofit = 0;
//         int maxprofit2 = 0;
//         int[][] dp = new int[prices.length][prices.length];

//         for(int i=0; i<prices.length;i++) {
//             for(int j = i+1; j<prices.length;j++){
//                 dp[i][j] = -1;
//             }
//         }

//         int maxB = 0;
//         int maxS = 0;

//         for(int i=0; i<prices.length;i++) {
//             for(int j = i+1; j<prices.length;j++){
//                 int profit = Math.max(prices[j]-prices[i],0);
//                 dp[i][j] = profit;
//                 int profit2 = 0;
//                 if(j+1<prices.length){
//                     profit2 = dp[i][j+1];
//                     if(dp[i][j+1] == -1) {
//                         profit2 = maxProfit2(prices,j+1);
//                         dp[i][j+1] = profit2;
//                     }

//                 }

//                 maxprofit2 = Math.max(maxprofit2,profit+profit2);

//                 System.out.println(i+":"+j+" "+profit + " "+profit2);
//             }
//         }




//         return maxprofit2;

        return maxProfitOp(prices);

    }

    public int maxProfit2(int[] prices,int pos){
        int maxprofit2 = 0;


        for(int i=pos; i<prices.length;i++) {
            for(int j = i+1; j<prices.length;j++){
                maxprofit2 = Math.max(prices[j]-prices[i],maxprofit2);
                System.out.println("**"+i+":"+j+" "+(prices[j]-prices[i]));


            }
        }
        return maxprofit2;
    }


    public int maxProfitOp(int[] prices) {

        if(prices==null || prices.length==0) return 0;
        int minbuy = prices[0];
        int maxprofit = 0;


        int[] dp1 = new int[prices.length];
        dp1[0] = 0;
        //dp1[1] = 0;
        for(int i=1; i<prices.length;i++) {
            maxprofit = Math.max(prices[i] - minbuy,maxprofit);
            dp1[i] = maxprofit;
            minbuy = Math.min(prices[i], minbuy);


        }

        int maxsell = prices[prices.length-1];
        maxprofit = 0;
        int[] dp2 = new int[prices.length];
        for(int i=prices.length-2; i>=0;i--) {
            maxprofit = Math.max(maxsell-prices[i],maxprofit);
            dp2[i] = maxprofit;
            maxsell = Math.max(prices[i], maxsell);

        }
        dp2[prices.length-1] = 0;

        //System.out.println(Arrays.toString(dp1));
        //System.out.println(Arrays.toString(dp2));


        maxprofit = 0;

        for(int i=0; i<prices.length;i++) {


            maxprofit = Math.max(dp1[i]+dp2[i],maxprofit);

            //System.out.println(i+":"+" " + " "+maxprofit);

        }




        return maxprofit;

    }

    public static void main(String[] args){

    }
}




