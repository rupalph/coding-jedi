import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rupalph on 8/26/19.
 */
public class NQueenRecursion {


    /*
     * Complete the function below.
     */
    static String[][] find_all_arrangements(int n) {
        //n-queeen arragement
        //queen can go up, down, left, right and diagonal

        List<String[]> output = new ArrayList<>();
        for(int j=0;j<n;) {
            char[][] board = createBoard(n);
            boolean res = arrange(n,0,j,board, false);
            if (res) {
                appendResult(output, board, new boolean[n][n]);
            }
            j=nextJ(board);
        }
        String[][] result = new String[output.size()][n];
        int i=0;
        for(String[] row:output){
            result[i++]=row;
        }
        return result;
    }

    private static String[][] solveNQueen(int n) {

        List<String[]> output = new ArrayList<>();


        char[][] board = createBoard(n);

        arrange2(n, 0, board, output);

        String[][] result = new String[output.size()][n];

        int i=0;
        for(String[] row:output){
            result[i++]=row;
        }
        return result;

    }
    private static int nextJ(char[][] board) {
        for(int j=0;j<board.length;j++){
            if(board[0][j]=='q')
                return j+1;
        }
        return board.length;
    }


    private static void appendResult(List<String[]> output, char[][] board, boolean[][] allArrangements) {
        String[] row = new String[board.length];

        for(int i=0;i<board.length;i++) {
            StringBuffer sb = new StringBuffer();

            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == 'q') {
                    allArrangements[i][j] = true;
                }

                sb.append(board[i][j]);

            }
            row[i] = sb.toString();
        }
        output.add(row);

    }

    private static char[][] createBoard(int n) {
        char[][] output = new char[n][n];


        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                output[i][j] = '-';
        return output;
    }

    private static void arrange2(int n, int i, char[][] output, List<String[]> result) {

        if(i==n) {
            appendResult(result, output, new boolean[n][n]);
            return;
        }

        for(int col=0;col<n;col++) {
            boolean check = check(output, n, i, col);
            if(check) {
                output[i][col] = 'q';
                arrange2(n, i + 1, output, result);
                output[i][col] = '-';
            }
        }



    }

    private static boolean arrange(int n, int i, int j, char[][] output, boolean canBePlaced) {
        if(i==n || j==n) return canBePlaced;
        boolean res = false;
        boolean check = check(output, n, i, j);
        if(check) {
            output[i][j] = 'q';
            res = arrange(n, i + 1, 0, output, true);
        }
        if(!check || !res) {
            output[i][j] = '-';
            res = arrange(n, i, j + 1, output, false);
        }
        return res;


    }

    private static boolean check(char[][] output, int n, int row, int col) {
        for(int j=0;j<n;j++) {
            if (j != col && output[row][j]=='q')
                return false;
        }
        for(int i=0;i<n;i++){
            if(i!=row && output[i][col]=='q')
                return false;
        }
        for(int p=0;p<n;p++){
            for(int q=0;q<n;q++){

                if(p==row && q==col){
                    continue;
                }

                if((output[p][q]=='q' )&& ((p+q== row+col) || (p-q == row-col))){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        String[][] out = solveNQueen(4);
        System.out.println(Arrays.deepToString(out));


    }
}
