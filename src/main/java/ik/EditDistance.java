package ik;

import java.util.Arrays;

/**
 * Created by rupalph on 12/2/19.
 */
public class EditDistance {
    static int levenshteinDistance(String strWord1, String strWord2) {
        /*
         * Write your code here.
         */
        //abc --> abd --> abde
        //kitten to sitting
        //i=0, j=0
        //i+1, j+1, +1
        //
        //start at index 0, try 3 options : insert, update or delete, go to next index, recurse till index = length at length if output = strWord2, stop found outcome
        //need to keep track of char array, total update done so far
        //find min

        return helper(strWord1,strWord2, 0, 0);

    }

    static int helper(String strWord1, String strWord2, int i, int j) {
        int min = 0;
        if(i == strWord1.length() && j==strWord2.length()) min=0;
        else if(i >= strWord1.length() || j >=strWord2.length()) min=Integer.MAX_VALUE;
        else {
            if (strWord1.charAt(i) != strWord2.charAt(j)) {
                //insert
                int res1 = helper(strWord1, strWord2, i, j + 1);
                //update
                int res2 = helper(strWord1, strWord2, i + 1, j + 1);
                //delete
                int res3 = helper(strWord1, strWord2, i + 1, j);
                //find min
                min = Math.min(Math.min(res1, res2), res3);
                if(min!=Integer.MAX_VALUE)
                    min=min+1;

            } else
                min=helper(strWord1, strWord2, i + 1, j + 1);
        }
        System.out.println("i="+i+",j="+j+",min="+min);
        return min;

    }

    static int helperDP(String strWord1, String strWord2) {
        int len1=strWord1.length();
        int len2=strWord2.length();
        int[][] dp = new int[len1+1][len2+1];

        int min = len1+len2;

        for(int row=0;row<=len1;row++)
            dp[row][0] = row;

        for(int col=0;col<=len2;col++)
            dp[0][col] = col;

        dp[0][0] = 0;
        for(int i=1;i<=len1;i++) {
            for (int j = 1; j <= len2; j++) {
                dp[i][j] = len1 + len2;
            }
        }

        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++) {
                //insert
                int res1 = dp[i][j - 1];
                //update
                int res2 = dp[i - 1][j - 1];
                //delete
                int res3 = dp[i - 1][j];
                //find min
                min = Math.min(Math.min(res1, res2), res3);
                if (strWord1.charAt(i-1) != strWord2.charAt(j-1)) {
                    System.out.println("not same");
                    dp[i][j] = min + 1;
                } else
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j-1]);
                System.out.println("i="+i+",j="+j+",min="+min);
            }
        }

        System.out.println(Arrays.deepToString(dp));
        return dp[len1][len2];

    }

    public static void main(String[] args){
//        int res = levenshteinDistance("kitten", "sitting");
        //int res1 = levenshteinDistance("abc", "abde");
        //int res = helperDP("abc", "abde");
//        int res3 = helperDP("kitten", "sitting");
        int res3 = helperDP("eq", "eqqqe");

        System.out.println(res3+" "+res3);
    }
}
