package ik;

import java.util.*;

/**
 * Created by rupalph on 9/6/19.
 */
public class ChessKnight {

    /*
 * Complete the function below.
 */
    static int find_minimum_number_of_moves(int rows, int cols, int start_row, int start_col, int end_row, int end_col) {
        // Write your code here.

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(start_row, start_col));
        int steps = -1;
        HashSet<String> visited = new HashSet<>();
        boolean reachedDest = false;
        while(!q.isEmpty() && !reachedDest) {
            int levelSize = q.size();
            steps++;
            while(levelSize-->0 && !reachedDest){
                Point pos = q.poll();
                if(visited.contains(pos.toString()))
                    continue;
                visited.add(pos.toString());
                if(pos.row==end_row && pos.col==end_col) {
                    reachedDest = true;
                    System.out.println(pos);
                }
                List<Point> moves = getPossibleMoves(pos, rows, cols);
                for(Point move:moves){
                    q.add(move);
                }
            }
        }
        return reachedDest?steps:-1;
    }

    private static List<Point> getPossibleMoves(Point pos, int rows, int cols) {
        int[] next_row = { -2, -2, -1, -1, 1, 1, 2, 2};
        int[] next_col = { 1, -1, -2, 2, -2, 2, 1, -1};
        List<Point> result = new ArrayList<>();
        for(int i=0;i<next_row.length;i++){
            Point new_loc = new Point(pos.row+next_row[i], pos.col+next_col[i]);
            if(withinBounds(new_loc, rows, cols)) {
                result.add(new_loc);
            }
        }
        return result;

    }

    private static boolean withinBounds(Point point, int rows, int cols) {
        if(point.row>=0 && point.row<rows && point.col>=0 && point.col < cols)
            return true;
        return false;
    }

    static class Point {
        int row;
        int col;

        public Point(int r, int c){
            row = r;
            col = c;
        }

        public String toString(){
            return "("+row+","+col+")";
        }
    }

    public static void main(String args[]){
//        int res = find_minimum_number_of_moves(8, 8, 6, 2, 1,4);
//        System.out.println(res);

        int res2 = find_minimum_number_of_moves(5, 5, 0, 0, 4, 1 );
        System.out.println(res2);
    }

}
