package ik;

import java.util.*;

/**
 * Created by rupalph on 9/9/19.
 */
public class ZombieClusters {

    /*
 * Complete the 'zombieCluster' function below.
 *
 * The function accepts STRING ARRAY as parameter.
 */
    public static int zombieCluster(List<String> zombies) {
        // Write your code here

        int n = zombies.size();
        int m = zombies.get(0).length();
        int count = 0;
        char[][] grid = new char[n][m];
        int allZero=0;
        int allOne=0;
        for(int i=0;i<n;i++) {
            for(int j = 0; j<m;j++){
                grid[i][j] = zombies.get(i).charAt(j);
                if(grid[i][j]=='1')
                    allOne++;
                if(grid[i][j]=='0')
                    allZero++;
            }
        }
        if(allOne==n*m) return 1;
        if(allZero==n*m) return 0;

        for(int i=0;i<n;i++) {
            for(int j = 0; j<m;j++){
                if(grid[i][j]=='1') {
                    countClusters(grid, i, j, n, m);
                    count++;
                }
            }
        }
        return count;
    }
    static void countClusters(char[][] grid, int row, int col, int n, int m) {

        grid[row][col] = '0';

        int i=row;
        int j=col;
        if(j+1<grid[i].length && grid[i][j+1]=='1')
            countClusters(grid, i,j+1,n,m);
        if(j-1>=0 && grid[i][j-1]=='1')
            countClusters(grid,i,j-1,n,m);
        if(i+1<grid.length && grid[i+1][j]=='1')
            countClusters(grid,i+1,j,n,m);
        if(i-1>=0 && grid[i-1][j]=='1')
            countClusters(grid,i-1,j,n,m);
    }




}
