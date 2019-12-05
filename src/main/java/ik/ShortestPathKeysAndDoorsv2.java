package ik;

import java.util.*;

/**
 * Created by rupalph on 9/6/19.
 */
public class ShortestPathKeysAndDoorsv2 {

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



    private static int[][] to2DArray(List<Cell> path) {

        int[][] res = new int[path.size()][2];
        int row =0;
        int col = 0;
        for(Cell cell:path){
            res[row][0] = cell.row;
            res[row++][1] = cell.col;
         }
         return res;
    }

    /*
       Main function does bfs in 2D Array.

       For 2D array BFS, use Queue of path.
       add current cell (row, col) to path and put it in queue

       while q is not empty iterate
           cur node = get first element from queue and get last node in the path
           find neighbors of cur node
           clone path from current path, append cur node to path and add path to queue
        */
    private static List<Cell> bfs(String[] grid,Cell cur){

        Queue<Path> q = new LinkedList<>();
        Path path = new Path(cur);
        q.offer(path);
        while(!q.isEmpty()) {
            path = q.poll();
            cur = path.last();
            if (cur.c == '+') {
//                System.out.println(path);
                return path.nodes;
            }
            else {
                List<Cell> neighbors = getNeighbors(cur, grid, path.keys);
                for (Cell cell : neighbors) {
                    if (!path.isVisited(cell)) {
                        Path path2 = new Path(path);
                        path2.visit(cell);
                        q.offer(path2);
                    }
                }
            }
        }
        return path.nodes;

    }



    /*
    Get neighbors in 2D matrix (top, left, right, bottom
    With Constraint
     */
    private static List<Cell> getNeighbors(Cell cur, String[] grid, Set<Character> keys) {
        int[] dir_x = { -1, 0, 0, 1};
        int[] dir_y = { 0, -1, 1, 0};
        List<Cell> neighbors = new ArrayList<>();
        for(int i=0;i<dir_x.length;i++){
            int x = cur.row+dir_x[i];
            int y = cur.col+dir_y[i];
            if(x>=0 && x<grid.length && y>=0 && y<grid[0].length()) {
                char c = grid[x].charAt(y);
                if((c>='A' && c<='Z' && keys.contains(Character.toLowerCase(c))) ||
                        c=='.' || c == '+' || c == '@'
                        || (c>='a' && c<='z')) {
                    neighbors.add(new Cell(grid[x].charAt(y), x, y, keys));
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
        Set<Character> keys=new HashSet<>();
        Cell parent;

        public String toString() {
            return c+":("+row+","+col+")"+":"+keys.toString();
        }

        public Cell(char me, int x, int y){
            row = x;
            col = y;
            c = me;
            if (c >= 'a' && c <= 'z') keys.add(c);
        }

        public Cell(char me, int x, int y, Set<Character> keys){
            this(me, x, y);
            this.keys.addAll(keys);
        }
    }


    /**
     * Helper class holds nodes in the path, visited node set and keys (lower case a-z)
     */
    static class Path {
        List<Cell> nodes;
        Set<String> visited;
        Set<Character> keys;

        public Path(Cell cell) {
            this.nodes = new ArrayList<>();
            nodes.add(cell);
            this.visited = new HashSet<>();
            visited.add(cell.toString());
            this.keys = new HashSet<>();
        }

        public Path(Path p){
            this.nodes = new ArrayList<>(p.nodes);
            this.visited = new HashSet<>(p.visited);
            this.keys = new HashSet<>(p.keys);
        }

        public void visit(Cell cell){
            visited.add(cell.toString());
            nodes.add(cell);
            if (cell.c >= 'a' && cell.c <= 'z')
                keys.add(cell.c);
        }

        public boolean isVisited(Cell cell){
            return visited.contains(cell.toString());
        }

        public Cell last() {
            return nodes.get(nodes.size()-1);
        }

        public String toString() {
            return nodes.toString();
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
