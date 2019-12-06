package algo;

public class Sudoku {
	public static boolean isValidSudoku(int[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9)
			return false;
		// check each column
		for (int i = 0; i < 9; i++) {
			boolean[] m = new boolean[9];
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != 0) {
					if (m[(int) (board[i][j] - 1)]) {
						return false;
					}
					m[(int) (board[i][j] - 1)] = true;
				}
			}
		}
	 
		//check each row
		for (int j = 0; j < 9; j++) {
			boolean[] m = new boolean[9];
			for (int i = 0; i < 9; i++) {
				if (board[i][j] != '.') {
					if (m[(int) (board[i][j] - 1)]) {
						return false;
					}
					m[(int) (board[i][j] - 1)] = true;
				}
			}
		}
	 
		//check each 3*3 matrix
		for (int block = 0; block < 9; block++) {
			boolean[] m = new boolean[9];
			System.out.println();
			// 0 : 00,01,02, 11,12,13, 20,21,22
			// 1 : 03,04,05, 13,14,15, 23,24,25
			// 2 : 06,07,08, 16,17,18, 26,27,28
			// 3 : 30,31,32, 40,41,42, 50,51,52
			// 4 : 33,34,35, ...
			for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
				for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
					System.out.print(board[i][j]+" ");
					if (board[i][j] != 0) {
						if (m[(int) (board[i][j] - 1)]) {
							return false;
						}
						m[(int) (board[i][j] - 1)] = true;
					}
				}
			}
		}
	 
		return true;
	}
	
	public static void main(String[] args){
		int[][] board = {

                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
            };
	
		boolean res=isValidSudoku(board);
		System.out.println(res);
	}
}
