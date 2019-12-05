package algo;
import java.util.Arrays;

public class PatternMatch {

	static class Grid  {
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.deepHashCode(grid);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Grid other = (Grid) obj;
			if (!Arrays.deepEquals(grid, other.grid))
				return false;
			return true;
		}

		private int[][] grid = new int[0][0];
		public Grid(int n,int m){
			grid = new int[n][m];
		}
	
		public Grid(int[][] matrix){
			grid = matrix;
		}

		public int getRowCount() {
			return grid.length;
		}

		public int getColCount() {
			return grid.length>0?grid[0].length:0;
		}

		public Grid getGrid(int row, int col, int patternWidth, int patternHeight) {
			int newMatrix[][] =new int[patternWidth][patternHeight];
			if(row+patternWidth<=getRowCount() && col+patternHeight <= getColCount()) {
				for(int i=0;i<patternWidth;i++){
					for(int j=0;j<patternHeight;j++){
						newMatrix[i][j] = grid[i+row][j+col];
					}
				}
			}
			return new Grid(newMatrix);
		}
		
		public void print()
		{
			System.out.println("********");
			for(int i=0;i<grid.length;i++){
				for(int j=0;j<grid[0].length;j++) {
					System.out.print(grid[i][j]);
				}
				System.out.println();
			}	
			System.out.println("");
		}
	
	}
	public static boolean findPattern(Grid matrix,Grid pattern){
		int patternRows=pattern.getRowCount();
		int patternCol=pattern.getColCount();
		int patternHash = pattern.hashCode();
		int row=0,col=0;
		for(row=0;row<=matrix.getRowCount()-patternRows;row++){
			for(col=0;col<=matrix.getColCount()-patternCol;col++){
				System.out.println("row:"+row);
				Grid curGrid = matrix.getGrid(row,col,patternRows,patternCol);
				curGrid.print();
				int curHash = curGrid.hashCode();
				if(curHash==patternHash)
					return true;
				
			}
		}
		
		return false;
	}
	public static void main(String[] args)
	{
		int[][] matrix = new int[][]{
			{7,2,8,3,4,5,5,8,6,4},
			{6,7,3,1,1,5,8,6,1,9},
			{8,9,8,8,2,4,2,6,4,3},
			{3,8,3,9,5,0,5,3,2,4},
			{9,5,0,9,5,0,5,8,1,3},
			{3,8,4,3,8,4,5,3,8,4},
			{6,4,7,3,5,3,0,2,9,3},
			{7,0,5,3,1,0,6,6,0,1},
			{0,8,3,4,2,8,2,9,5,6},
			{4,6,0,7,9,2,4,1,3,7}
			};

		int[][] patterns = new int[][] { 
			{ 9, 5, 0 }, 
			{ 3, 8, 4 }, 
			{ 3, 5, 3 } };
	
	
		Grid matrixGrid = new Grid(matrix);
		Grid patternGrid = new Grid(patterns);
		
		System.out.println("found pattern:"+findPattern(matrixGrid,patternGrid));
	}
		
}
