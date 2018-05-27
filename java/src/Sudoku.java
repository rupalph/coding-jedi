/**
 * Created by rupalh on 7/30/17.
 */
public class Sudoku {
    public static void main(String[] args){
        Sudoku s=new Sudoku();
        int[][] board ={ {5,3,0,0,7,0,0,0,0},
                {6,0,0,1,9,5,0,0,0},
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}};
        System.out.println("Sudoku valid "+s.isValidSudoku(board));
    }
    public boolean isValidSudoku(int[][] board) {

        for(int i=0;i<9;i++){
            if (!isValid(board[i])) return false;
        }
        for(int i=0;i<9;i++){
            int col[] =new int[9];

            for(int j=0;j<9;j++) {
                col[j]=board[j][i];
            }
            if(!isValid(col)) return false;
        }
        for(int i=0;i<9;i++){
            int box[] =new int[9];

            for(int j=0;j<3;j++) {
                for(int k=0;k<3;k++) {

                    int cal_i = 3*(i/3)+j;
                    int cal_j = 3*(i%3)+k;
                    //1,0
                    System.out.print(cal_i + "," + cal_j);
                    System.out.println("=" + board[cal_i][cal_j]);
                    box[j+k]=board[cal_i][cal_j];

                }
            }
            if(!isValid(box)) return false;
            System.out.println();
        }
        return true;
    }

    private boolean isValid(int[] input) {
        boolean[] array=new boolean[10];
        for(int j=0;j<9;j++) {


            if(input[j]!=0) {
               // System.out.println(i+","+j+"="+board[i][j]);
                if (array[input[j]])
                    return false;
                else
                    array[input[j]]=true;
            }
        }
        return true;
    }
}
