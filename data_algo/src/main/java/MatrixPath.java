import java.util.LinkedList;
import java.util.List;

/**
 * Created by rupalph on 8/27/19.
 */
public class MatrixPath {

    static void findAllPaths(int n, int m, int row, int col, LinkedList<String> paths){
        if(row==n-1 && col==m-1){
            System.out.println(paths);
        }
        if(row==n||col==m) return;
        paths.add((row+1)+","+col);
        findAllPaths(n,n,row+1,col,paths);
        paths.removeLast();
        paths.add(row+","+(col+1));
        findAllPaths(n,n,row,col+1,paths);
        paths.removeLast();

    }

    public static void main(String[] args){
        LinkedList<String> paths = new LinkedList<>();
        paths.add("0,0");
        findAllPaths(4,4,0,0,paths);
    }
}
