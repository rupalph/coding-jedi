/**
 * Created by rupalh on 4/26/18.
 */
public class WordTranslate {
    public int translate(String s1, String s2){
        int dp[][] = new int[s1.length()+1][s2.length()+1];
        for(int i = 0; i<s1.length()+1; i++)
            dp[i][0] = i;
        for(int i = 0; i<s2.length()+1; i++)
            dp[0][i] = i;

        for(int i=1; i<s1.length()+1; i++){
            for(int j=1; j<s2.length()+1; j++) {
                char c1 = s1.charAt(i-1);
                char c2 = s2.charAt(j-1);
                System.out.println(c1+","+c2+":"+" "+dp[i-1][j]+" "+dp[i][j-1]+" "+dp[i-1][j-1]);
                if(c1==c2)
                    dp[i][j] = dp[i-1][j-1];

                else
                    dp[i][j]= Math.min(Math.min(dp[i][j-1],dp[i-1][j]),dp[i-1][j-1])+1;
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args){
        WordTranslate w = new WordTranslate();
        int result = w.translate("horse","ros");
        System.out.println(result);
    }
}
