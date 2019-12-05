package ik;

import java.util.*;

/**
 * Created by rupalph on 9/6/19.
 */
public class ShortestPathKeysAndDoors {

    static int[][] find_shortest_path(String[] grid) {
        List<Cell> path = new ArrayList<>();
        Set<String> visited= new HashSet<>();
        Set<Character> keys= new HashSet<>();
        Set<String> exploring= new HashSet<>();

        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length();j++){
                if(grid[i].charAt(j) == '@') {
                    Cell c = new Cell(grid[i].charAt(j), i, j);
                    dfs(grid, c, path, keys, visited, exploring);
                    break;
                }
            }
        }
//        System.out.println(path);
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

    private static boolean dfs(String[] grid,Cell cur, List<Cell> path, Set<Character> keys,Set<String> visited, Set<String> exploring){
        if(exploring.contains(cur.toString()))
            return false;
        exploring.add(cur.toString());
        if(cur.c>='a' && cur.c<='z') keys.add(cur.c);
        visited.add(cur.toString());
        path.add(cur);
        boolean res = false;
        List<Cell> neighbors = getNeighbors(cur, grid, keys);
        for(Cell cell:neighbors){
            char c = cell.c;
            if(c=='+') {
                path.add(cell);
                return true;
            }
            else if(!visited.contains(cell.toString())) {

                res = dfs(grid, cell, path, keys, visited, exploring);
                if(res) return true;
                path.remove(cell);
                visited.remove(cell.toString());
                keys.remove(c);
            }
        }
        exploring.remove(cur.toString());
        return res;

    }



    private static List<Cell> getNeighbors(Cell cur, String[] grid, Set<Character> keys) {
        int[] dir_x = { -1, 0, 0, 1};
        int[] dir_y = { 0, -1, 1, 0};
        List<Cell> neighbors = new ArrayList<>();
        for(int i=0;i<dir_x.length;i++){
            int x = cur.row+dir_x[i];
            int y = cur.col+dir_y[i];
            if(x>=0 && x<grid.length && y>=0 && y<grid[0].length()) {
                char c = grid[x].charAt(y);
                if((c>='A' && c<='Z' && keys.contains(Character.toLowerCase(c)))
                            || c=='.' || c == '+'
                            || (c>='a' && c<='z'))
                    neighbors.add(new Cell(grid[x].charAt(y), x, y));
            }
        }
        return neighbors;
    }

    static class Cell {
        char c;
        int row, col;
        Set<Character> keys=new HashSet<>();

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
            row = x;
            col = y;
            c = me;
            this.keys = new HashSet<>(keys);
        }
    }

    static class State {
        char c;
        int row, col;
        Set<Character> keys;

        public String toString() {
            return c+":("+row+","+col+")";
        }

        public State(char me, int x, int y, Set<Character> keys){
            row = x;
            col = y;
            c = me;
        }
    }

    static class Path {
        List<Cell> nodes;
        Set<String> visited;
        Set<Character> keys;

        public Path(Cell cell) {
            this.nodes = new ArrayList<>();
            nodes.add(cell);
            this.visited = new HashSet<>();
            this.keys = new HashSet<>();
        }

        public Path(Path p){
            this.nodes = new ArrayList<>(p.nodes);
            this.visited = new HashSet<>(p.visited);
            this.keys = new HashSet<>(p.keys);
        }

        public void visit(Cell c){
            visited.add(c.toString());
        }

        public boolean isVisited(Cell cell){
            return visited.contains(cell.toString());
        }

        public void addKey(Cell cell){
            keys.add(cell.c);
            cell.keys.add(cell.c);
        }

        public Cell last() {
            return nodes.get(nodes.size()-1);
        }

        public void add(Cell cell) {
            nodes.add(cell);
        }

        public String toString() {
            return nodes.toString();
        }
    }

    private static List<Cell> getNeighbors2(Cell cur, String[] grid, Set<Character> keys) {
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
                        || (c>='a' && c<='z'))
                    neighbors.add(new Cell(grid[x].charAt(y), x, y, keys));
            }
        }
        return neighbors;
    }
    static int[][] find_shortest_path_bfs(String[] grid) {
        List<Cell> path = new ArrayList<>();

        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length();j++){
                if(grid[i].charAt(j) == '@') {
                    Cell c = new Cell(grid[i].charAt(j), i, j);
                    path = bfs2(grid, c);
                    break;
                }
            }
        }
        return to2DArray(path);
    }

    private static boolean isNotVisited(Cell x, List<Cell> path)
    {
        int size = path.size();
        for (int i = 0; i < size; i++)
            if (path.get(i).toString().equals(x))
                return false;
        return true;
    }
    private static boolean bfs(String[] grid,Cell cur, List<Cell> path3, Set<Character> keys,Set<String> visited){


        Queue<List<Cell>> q = new LinkedList<>();
        List<Cell> path = new ArrayList<>();
        path.add(cur);
        q.offer(path);
        while(!q.isEmpty()) {
            path = q.poll();
            cur = path.get(path.size()-1);
            visited.add(cur.toString());
            if (cur.c >= 'a' && cur.c <= 'z') keys.add(cur.c);

            if (cur.c == '+') {
                //path.add(cur);
                //System.out.println(path);
                return true;
            }
            else {

                List<Cell> neighbors = getNeighbors2(cur, grid, keys);
                for (Cell cell : neighbors) {
                    if (isNotVisited(cell, path)) {
                        List<Cell> path2 = new ArrayList<>(path);
                        path2.add(cell);
                        q.offer(path2);
                    }
                }
            }
        }
        return false;

    }

    private static List<Cell> bfs2(String[] grid,Cell cur){

        Queue<Path> q = new LinkedList<>();
        Path path = new Path(cur);
        q.offer(path);
        while(!q.isEmpty()) {
            path = q.poll();
            cur = path.last();

            path.visit(cur);

            if (cur.c == '+') {
//                path.add(cur);
                System.out.println(path);
                return path.nodes;
            }
            else {
                List<Cell> neighbors = getNeighbors2(cur, grid, path.keys);
                for (Cell cell : neighbors) {
                    if (!path.isVisited(cell)) {
                        Path path2 = new Path(path);
                        path2.add(cell);
                        if (cell.c >= 'a' && cell.c <= 'z') path2.addKey(cell);
                        q.offer(path2);
                    }
                }
            }
        }
        return path.nodes;

    }

    public static void main(String[] args){
//        int[][] res = find_shortest_path_bfs(new String[]{"...B", ".b#.", "@#+."});
//        System.out.println(Arrays.deepToString(res));
//
//        int[][] res2 = find_shortest_path_bfs(new String[]{"+B...", "####.", "##b#.", "a...A", "##@##"});
//        System.out.println(Arrays.deepToString(res2));

        int[][] res3 = find_shortest_path_bfs(new String[]{".dj##.f.j#efejj..@e#+G.c.", ".hdI#.#aAghficDe#J.CGa.ba"});
        System.out.println(Arrays.deepToString(res3));

        int[][] res4 = find_shortest_path_bfs(new String[]{"@.B", "b#+"});
        System.out.println(Arrays.deepToString(res4));
    }
}
