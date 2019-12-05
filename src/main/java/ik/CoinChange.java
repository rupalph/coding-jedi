package ik;

/**
 * Created by rupalph on 9/12/19.
 */
public class CoinChange {

    public  static int coinChange(int amount, int[] coins){

        int[] dp = new int[amount+1];

        dp[0] = 0;

        for(int i =1;i<dp.length;i++) dp[i] = Integer.MAX_VALUE;

        for(int i = 1; i < dp.length; i++) {
            for( int j = 0; j<coins.length;j++){
                if(coins[j]<=i) {
                    int k = dp[i - coins[j]];
                    if (k != Integer.MAX_VALUE && k + 1 < dp[i])
                        dp[i] = k + 1;
                }
            }
        }

        printCoins(dp, coins);
        return dp[dp.length-1];
    }

    public  static int coinChangeRecursive(int amount, int[] coins){

        if(amount==0) return 0;
        if(amount<0) return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<coins.length;i++){
            min = Math.min(min, coinChangeRecursive(amount - coins[i], coins));
        }

        return min == Integer.MAX_VALUE? Integer.MAX_VALUE : min+1;
    }

    /*

    cc (8, 0)

    cc (7, 1)

    cc (3, 1)

    cc(-2, 1)

    cc(6,2) cc(1,2) cc(-4,2)

     */
    public  static int coinChangeRecursive2(int amount, int[] coins){
        System.out.println(amount+" ");
        if(amount ==0) return 0;
        int min = Integer.MAX_VALUE;
        int count;
        for(int i=0;i<coins.length;i++){
            if(amount-coins[i]>=0) {
                count = coinChangeRecursive2(amount - coins[i], coins);
                min = count + 1;
            }
        }
        return min==Integer.MAX_VALUE?-1:min;
    }

    private static void printCoins(int[] dp, int[] coins) {

        int amount = dp.length-1;
        for(int i=dp.length-1;i>=0&&amount>0;){

            for(int j=0;j<coins.length;j++){
                int k = amount - coins[j];
                if(dp[k]>=dp[i]) continue;
                System.out.print(coins[j]+" ");
                i -= coins[j];
                amount = k;
                break;
            }
        }
    }

    public static void main(String[] args){
//        int count = coinChange(8, new int[] {1, 5, 10, 25});
//        System.out.println("\n"+count);

//        int count2 = coinChangeRecursive2(8, 0, new int[] {2,3,5});
//        System.out.println("\n"+count2);

        int count = coinChangeRecursive2(8,new int[] {1, 5, 10, 25});
        System.out.println("\n"+count);
    }
}
