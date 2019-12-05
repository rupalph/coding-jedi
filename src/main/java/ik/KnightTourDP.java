package ik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rupalph on 9/16/19.
 */
public class KnightTourDP {

    static long numPhoneNumbers(int startdigit, int phonenumberlength) {
        /*
         * Write your code here.
         */
        
        int[][] input = initializePhonePad();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int k = 1;
        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[0].length;j++){
                if(input[i][j]!=-1)
                    getPossibleMoves(i, j, input, map);
            }
        }

//        return numPhoneNumbersRecursive(startdigit, phonenumberlength, 0, map,"");
        return numPhoneNumbersDP2(startdigit, phonenumberlength, map);
    }

    private static int[][] initializePhonePad() {
        int[][] phonePad = new int[][] { { 1,2,3}, {4,5,6}, {7,8,9},{-1,0,-1}};
        return phonePad;
    }


    static long numPhoneNumbersRecursive(int startdigit, int phonenumberlength,int count, Map<Integer, List<Integer>> map, String prefix) {
        /*
         * Write your code here.
         */

//        System.out.print(startdigit);
        int res = count;
        if(phonenumberlength == 1) {
            System.out.println(prefix+startdigit);
            return 1;
        }


        for(Integer nextNum : map.get(startdigit)){
            res += numPhoneNumbersRecursive(nextNum, phonenumberlength-1, count, map, prefix+startdigit);
        }
        return res;

    }


    static long numPhoneNumbersDP(int startdigit, int phonenumberlength,Map<Integer, List<Integer>> map) {
        /*
         * Write your code here.
         */

//        System.out.print(startdigit);
//        int[][] dp = new int[10][phonenumberlength+1];
//        for(int row=0;row<10;row++) {
//            dp[row][0]=0;
//            dp[row][1]=1;
//            dp[row][2] = map.get(row).size();
//        }
        Map<Integer, List<Integer>> hopMap = new HashMap<>();
        hopMap.computeIfAbsent(1,k->new ArrayList<>()).add(startdigit);
//        hopMap.put(2, map.get(startdigit));
        for(int hop = 2;hop<=phonenumberlength;hop++){
            List<Integer> nextdigits = hopMap.get(hop-1);
            for(int nextDigit:nextdigits){
                hopMap.computeIfAbsent(hop, k->new ArrayList<>()).addAll(map.get(nextDigit));
            }
        }

        System.out.println(hopMap.get(phonenumberlength));
        return hopMap.get(phonenumberlength).size();
    }


    static long numPhoneNumbersDP2(int startdigit, int phonenumberlength,Map<Integer, List<Integer>> map) {
        /*
         * Write your code here.
         */

//        System.out.print(startdigit);
        int[][] dp = new int[10][phonenumberlength + 1];
        dp[startdigit][1] = 1;
        for(int len=2;len<=phonenumberlength;len++) {
            for (int digit = 0; digit < 10; digit++) {
                for (int nextDigit : map.get(digit)) {
                    dp[digit][len] += dp[nextDigit][len - 1];
                }
            }
        }
        int sum = 0;
        for (int digit = 0; digit < 10; digit++) {
            sum += dp[digit][phonenumberlength];
        }
        return sum;
    }


    private static Map<Integer, List<Integer>> getPossibleMoves(int row, int col, int[][] phonePad ,Map<Integer, List<Integer>> map) {
        int[] next_row = { -2, -2, -1, -1, 1, 1, 2, 2};
        int[] next_col = { 1, -1, -2, 2, -2, 2, 1, -1};
        int rows = phonePad.length;
        int cols = phonePad[0].length;
        for(int i=0;i<next_row.length;i++){
            int new_row = row+next_row[i];
            int new_col = col+next_col[i];
            if(new_row>=0 && new_row<rows && new_col>=0 && new_col < cols && phonePad[new_row][new_col] != -1){
                map.computeIfAbsent(phonePad[row][col], k-> new ArrayList<>()).add(phonePad[new_row][new_col]);
            }

        }
        map.computeIfAbsent(phonePad[row][col], k-> new ArrayList<>());
        return map;

    }

    private static boolean withinBounds(ChessKnight.Point point, int rows, int cols) {
        if(point.row>=0 && point.row<rows && point.col>=0 && point.col < cols)
            return true;
        return false;
    }

    private static class PhoneLoc {
        private int row;
        private int col;
        public PhoneLoc(int i, int j) {
            row = i;
            col = j;
        }
    }

    public static void main(String[] args){
        long res = numPhoneNumbers(1,2);
        System.out.print(res);
    }
}
