package ik;

import java.util.*;

/**
 * Created by rupalph on 9/8/19.
 */
public class FindOrderAllienDictionary {

    private static class Node {
        private char id;

        public void updateColor() {
            this.color++;
        }

        private int color = 0;

        public Node(char a) {
            this.id = a;
        }

        public char getId() {
            return id;
        }

        public void setId(char id) {
            this.id = id;
        }

        public List<Node> getNeighbors() {
            return neighbors;
        }

        public void setNeighbors(List<Node> neighbors) {
            this.neighbors = neighbors;
        }

        private List<Node> neighbors = new ArrayList<>();

        public void addNeighbor(Node c){
            neighbors.add(c);
        }

        public int getColor() {
            return color;
        }
    }
    private static int max_indegree = 0;
    static String find_order(String[] words) {
        Map<Character, List<Character>> depMap = getDep(words);
        Map<Integer, List<Character>> indegree = getIndegree(depMap);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= max_indegree; i++) {
            List<Character> chars = indegree.get(i);
            if (chars != null) {
                Iterator<Character> it = chars.iterator();
                while (it.hasNext()) {
                    char c = it.next();
                    it.remove();
                    sb.insert(0, c);

                }
            }
        }
        return sb.toString();
    }

    private static Map<Integer, List<Character>> getIndegree(Map<Character, List<Character>> adjList) {
        Map<Integer,List<Character>> indegreeMap = new HashMap<>();

        for(char course:adjList.keySet()){
            int indegree=adjList.get(course).size();
            if(indegreeMap.containsKey(indegree)) {
                List<Character> list = indegreeMap.get(indegree);
                list.add(course);

            }
            else {
                List<Character> list = new ArrayList<>();
                list.add(course);
                indegreeMap.put(indegree,list);
            }
            if(max_indegree<indegree) max_indegree = indegree;

        }
        return indegreeMap;
    }


    private static Map<Character, List<Character>> getDep(String[] words) {
        Map<Character,List<Character>> adjList = new HashMap<>();
        for(int i = 0; i+1<words.length;i++){
            String word = words[i];
            String word2 = words[i+1];
            if(word.equals(word2)) continue;
            int j = 0;
            while(word.charAt(j)==word2.charAt(j++));
            char a = word.charAt(j-1);
            char b = word2.charAt(j-1);
            //a depends on b or a comes before b
            if(adjList.containsKey(a)){
                List<Character> list = adjList.get(a);
                list.add(b);

            }
            else{
                List<Character> list = new ArrayList<>();
                list.add(b);
                adjList.put(a,list);
            }
            if(!adjList.containsKey(b)) {
                List<Character> list = new ArrayList<>();
                adjList.put(b,list);
            }

        }

        return adjList;
    }


    static String find_order2(String[] words) {

        Map<Character, Node> graph = getDep2(words);
        //StringBuilder sb = new StringBuilder();
        return topoSort(graph);
    }

    private static Map<Character, Node> getDep2(String[] words) {
        Map<Character,Node> adjList = new HashMap<>();
        for(int i = 0; i<words.length;i++){
            if(i+1<words.length) {
                String word = words[i];
                String word2 = words[i + 1];
                if (word.equals(word2)) continue;
                int j = 0;
                while (word.charAt(j) == word2.charAt(j++)) ;
                char a = word.charAt(j - 1);
                char b = word2.charAt(j - 1);
                //a depends on b or a comes before b
                Node n = adjList.getOrDefault(a, new Node(a));
                Node m = adjList.getOrDefault(b, new Node(b));
                n.addNeighbor(m);
                adjList.putIfAbsent(a, n);
                adjList.putIfAbsent(b, m);

            }
            for(int j=0; j<words[i].length();j++){
                char c = words[i].charAt(j);
                adjList.putIfAbsent(c,new Node(c));
            }
        }


        return adjList;
    }

    private static  String topoSort(Map<Character, Node> graph){
        StringBuilder sb = new StringBuilder();
        for(Character letter:graph.keySet() ){
            Node n = graph.get(letter);
            if(n.getColor()==0)
                dfs(n, sb);

        }
        return sb.toString();
    }

    private static void dfs(Node n, StringBuilder sb) {
        n.updateColor();
        for(Node neighbor:n.getNeighbors()){
            if(neighbor.getColor() == 1)
                return; // cycle
            if(neighbor.getColor() == 0)
                dfs(neighbor, sb);
        }
        n.updateColor();
        sb.insert(0,n.id);
    }

    public static void main(String[] args){
        String order = find_order2(new String[] {"baa", "abcd", "abca", "cab", "cad"});
        System.out.println(order);


        String order2 = find_order2(new String[] {"a"});
        System.out.println(order2);
    }



}
