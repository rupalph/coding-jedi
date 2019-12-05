import java.util.*;

/**
 * Created by rupalph on 8/12/19.
 */
public class RemoveObstacle {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    class Point{
        int row;
        int col;
        int dist;
        public Point(int r, int c){
            row=r;
            col=c;
            dist = 0;
        }
        public Point(int r, int c, int d){
            row=r;
            col=c;
            dist = d;
        }
        public String toString(){
            return row+","+col;
        }
    }
//    List<Point> steplist = new ArrayList<>();
//    List<Integer> pathlist = new ArrayList<>();

    int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot, int row, int col, int steps,boolean[][] visited,List<Point> steplist,List<Integer> pathlist)
    {
        // WRITE YOUR CODE HERE
        if(row<0 || row >= lot.size() || col <0 || col>=lot.get(row).size())
            return -1;

                int val = lot.get(row).get(col);
//        System.out.println(val+":"+row+":"+col);

            if(visited[row][col]==true)
                    return -1;
                else if(val == 0) {
                    return -1;
                }
                else if(val ==9) {
//                    steplist.add(new Point(row,col));
                    pathlist.add(steplist.size());
                System.out.println("visit" + new Point(row,col));

                System.out.println(steplist);

//                pathlist.clear();
//                clearVisited(numRows,numColumns,visited);
                    return steps;
                }
                else if(val==1) {
                    visited[row][col] = true;
                    steplist.add(new Point(row,col));
                    System.out.println("visit" + new Point(row,col));
                    //System.out.println(steplist);
                    int res1 = removeObstacle(numRows, numColumns, lot, row, col - 1, steps + 1, visited,steplist,pathlist);
                    //if(res1>0)
                        //
                    int res2= removeObstacle(numRows, numColumns, lot, row, col + 1, steps + 1, visited,steplist, pathlist);
//                    if(res2>0)
//                        //steplist.add(new Point(row,col+1));
                    int res3= removeObstacle(numRows, numColumns, lot, row - 1, col, steps + 1, visited,steplist,pathlist);
//                    if(res3>0)
//                        steplist.add(new Point(row-1, col));
                    int res4= removeObstacle(numRows, numColumns, lot, row + 1, col, steps + 1, visited,steplist,pathlist);
//                    if(res4>0)
//                        steplist.add(new Point(row+1, col));

                }
        Collections.sort(pathlist);
        return pathlist.isEmpty()?-1:pathlist.get(0);

    }

    public void clearVisited(int n, int m, boolean[][] visited, List<Point> steplist){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++)
                if(steplist.contains(new Point(i,j)))
                    visited[i][j]=false;
        }
    }
    int removeObstacle2(int numRows, int numColumns, List<List<Integer>> lot)
    {
        boolean[][] visited = new boolean[numRows][numColumns];
        for(int i=0;i<lot.size();i++){
            for(int j=0;j<lot.get(i).size();j++)
                visited[i][j]=false;
        }
        //steplist.add(new Point(0,0));
        return removeObstacle(numRows, numColumns, lot, 0, 0, 0, visited,new ArrayList<Point>(),new ArrayList<>());
    }

    int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot)
    {
        boolean[][] visited = new boolean[numRows][numColumns];
        for(int i=0;i<lot.size();i++){
            for(int j=0;j<lot.get(i).size();j++)
                visited[i][j]=false;
        }
        //steplist.add(new Point(0,0));
        return bfs(numRows, numColumns, lot, visited);
    }
    int rowNum[] = {-1, 0, 0, 1};
    int colNum[] = {0, -1, 1, 0};
    int bfs(int numRows, int numColumns, List<List<Integer>> lot,boolean[][] visited)
    {
        // check source and destination cell
        // of the matrix have value 1

        int minDist = Integer.MAX_VALUE;

        // Mark the source cell as visited
        visited[0][0] = true;

        // Create a queue for BFS
        Queue<Point> q = new LinkedList<>();

        // Distance of source cell is 0
        Point s = new Point(0,0,0);
        q.offer(s);  // Enqueue source cell

        // Do a BFS starting from source cell
        while (!q.isEmpty())
        {
            Point curr = q.poll();

            for (int i = 0; i < rowNum.length; i++)
            {
                int row = curr.row + rowNum[i];
                int col = curr.col + colNum[i];

                // if adjacent cell is valid, has path and
                // not visited yet, enqueue it.
                if (isValid(row, col, lot)&&
                        !visited[row][col])
                {
                    // mark cell as visited and enqueue it
                    visited[row][col] = true;
                    if(lot.get(row).get(col)==1) {
                        Point Adjcell = new Point(row, col,
                                curr.dist + 1);
                        q.offer(Adjcell);
                    }
                    //System.out.println("visit " + new Point(row,col));
                    //System.out.println(q);
                    if (lot.get(row).get(col)==9) {
                        if(curr.dist+1<minDist)
                            minDist = curr.dist+1;

                    }

                }
            }
        }


        // Return -1 if destination cannot be reached
        return minDist;
    }

    private boolean isValid (int row,int col, List<List<Integer>> lot){
        if(row<0 || row >= lot.size() || col <0 || col>=lot.get(row).size())
            return false;
        return true;
    }
    public static void main(String[] args){
        RemoveObstacle t = new RemoveObstacle();
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(new Integer[]{1,0,0}));
        list.add(Arrays.asList(new Integer[]{1,0,0}));
        list.add(Arrays.asList(new Integer[]{1,9,1}));

        int ans = t.removeObstacle(3,3,list);
        System.out.println(ans);


        List<List<Integer>> list2 = new ArrayList<>();
        list2.add(Arrays.asList(new Integer[]{1,1,1, 1}));
        list2.add(Arrays.asList(new Integer[]{0,1,1, 1}));
        list2.add(Arrays.asList(new Integer[]{0,1,0, 1}));
        list2.add(Arrays.asList(new Integer[]{1,1,9, 1}));
        list2.add(Arrays.asList(new Integer[]{0,0,1, 1}));

        ans = t.removeObstacle(5,4,list2);
        System.out.println(ans);
    }
}
