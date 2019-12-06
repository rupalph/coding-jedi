package leetcode;

/**
 * Created by rupalph on 12/1/19.
 */
public class CountSubmatrixAllOnes {

    public static int count(int[][] matrix){
        int[][] dp = new int[matrix.length][matrix[0].length];

        int result = 0;

        for(int i=0;i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j]==1) {
                    int min = matrix[i][j];
                    if(i>0 && j>0)
                        min = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1])+1;
                    result += min;
                    dp[i][j] = min;
                }
            }
        }
        return result;

    }

    public static void main(String[] args){
        int[][] m1= new int[][] {
                {0,1,1,1},
                {1,1,1,1},
                {0,1,1,1}
        }  ;
        int res1= count(m1);
        System.out.println(res1);
    }
}
