package ik;

import java.util.*;

/**
 * Created by rupalph on 9/6/19.
 */
public class ShortestPathKeysAndDoorsv3 {

    static int[][] find_shortest_path_bfs(String[] grid) {
        List<Cell> path = new ArrayList<>();

        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length();j++){
                if(grid[i].charAt(j) == '@') {
                    Cell c = new Cell(grid[i].charAt(j), i, j);
                    path = bfs(grid, c);
                    break;
                }
            }
        }
        return to2DArray(path);
    }


    /*
    Convert path from reverse order list : List<Cell> to 2D array for e.g [[0,1],[1,2].. ]
     */
    private static int[][] to2DArray(List<Cell> path) {

        int[][] res = new int[path.size()][2];
        int row =0;
        int col = 0;
        for(int i=path.size()-1;i>=0;i--){
            Cell cell = path.get(i);
            res[row][0] = cell.row;
            res[row++][1] = cell.col;
         }
         return res;
    }

    /*
       Main function does bfs in 2D Array.

       For 2D array BFS, use Queue of node (node should store parent pointer).
       add current cell (row, col) to the queue
       mark cur node visited and also store the door keys got so far in visited set.
       while q is not empty iterate
           cur node = get first element from queue and get last node in the path
           find neighbors of cur node
           for each neighbor, append neighbor node to the queue
       returns shotest path with given constraint in reverse order
        */
    private static List<Cell> bfs(String[] grid,Cell cur){

        Queue<Cell> q = new LinkedList<>();
        q.offer(cur);
        Set<String> visited = new HashSet<>();
        while(!q.isEmpty()) {
            cur = q.poll();
            visited.add(cur.toString());
            if (cur.c == '+') {
                //System.out.println(cur);
                return toPath(cur);
            }
            else {
                List<Cell> neighbors = getNeighbors(cur, grid, cur.key_mask);
                for (Cell cell : neighbors) {
                    if (!visited.contains(cell.toString())) {
                        cell.parent = cur;
                        q.offer(cell);
                    }
                }
            }
        }
        return new ArrayList<>();

    }

    private static List<Cell> toPath(Cell cur) {
        Cell p = cur;
        List<Cell> res = new ArrayList<>();
        while(p!=null){
            System.out.print(p+" ");
            res.add(p);
            p = p.parent;
        }
        System.out.println();
        return res;
    }


    /*
    Get neighbors in 2D matrix (top, left, right, bottom
    With Constraint
     */
    private static List<Cell> getNeighbors(Cell cur, String[] grid, int keymask) {
        int[] dir_x = { -1, 0, 0, 1};
        int[] dir_y = { 0, -1, 1, 0};
        List<Cell> neighbors = new ArrayList<>();
        for(int i=0;i<dir_x.length;i++){
            int x = cur.row+dir_x[i];
            int y = cur.col+dir_y[i];
            if(x>=0 && x<grid.length && y>=0 && y<grid[0].length()) {
                char c = grid[x].charAt(y);
                if((c>='A' && c<='Z'
                        && (keymask&(1<<Character.toLowerCase(c)-'a'))!=0) ||
                        c=='.' || c == '+' || c == '@'
                        || (c>='a' && c<='z')) {
                    neighbors.add(new Cell(grid[x].charAt(y), x, y, keymask));
                }
            }
        }
        return neighbors;
    }



    /**
     * Helper class to hold current cell value (c), row, column and keys (Keys and Door problem only)
     */
    static class Cell {
        char c;
        int row, col;
//        Set<Character> keys=new HashSet<>();
        int key_mask=0;
        Cell parent;

        public String toString() {
            return c+":("+row+","+col+")"+":"+key_mask;
        }

        public Cell(char me, int x, int y){
            row = x;
            col = y;
            c = me;
            if (c >= 'a' && c <= 'z')
                key_mask |= (1 << c - 'a');
        }

//        public Cell(char me, int x, int y, Set<Character> keys){
//            this(me, x, y);
//            this.keys.addAll(keys);
//        }

        public Cell(char me, int x, int y, int mask){
            this(me, x, y);
            this.key_mask = key_mask | mask;
        }
    }



    public static void main(String[] args){
        int[][] res = find_shortest_path_bfs(new String[]{"...B", ".b#.", "@#+."});
        System.out.println(Arrays.deepToString(res));
//
        int[][] res2 = find_shortest_path_bfs(new String[]{"+B...", "####.", "##b#.", "a...A", "##@##"});
        System.out.println(Arrays.deepToString(res2));



        int[][] res3 = find_shortest_path_bfs(new String[]{".dj##.f.j#efejj..@e#+G.c.", ".hdI#.#aAghficDe#J.CGa.ba"});
        System.out.println(Arrays.deepToString(res3));
//
        int[][] res4 = find_shortest_path_bfs(new String[]{"@.B", "b#+"});
        System.out.println(Arrays.deepToString(res4));
    }
}
