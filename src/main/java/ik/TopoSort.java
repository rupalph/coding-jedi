package ik;

import java.util.*;

/**
 * Created by rupalph on 9/26/19.
 */
public class TopoSort {
    static class Graph
    {
        private int V;   // No. of vertices
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        Graph(int v) {
            V = v;
            adjList = new HashMap<>();
            for (int i = 0; i < v; ++i)
                adjList.put(i, new LinkedList());
        }
        // Function to add an edge into the graph
        void addEdge(int v,int w) { adjList.computeIfAbsent(v, list -> new LinkedList<>()).add(w); }
    }



    private static void topoSortDfs(int graphNode, Map<Integer,List<Integer>> adjList, LinkedList<Integer> order, Set<Integer> visited, Set<Integer> exploring){


        if(exploring.contains(graphNode))
            return;
        visited.add(graphNode);
        exploring.add(graphNode);
        List<Integer> neighbors = adjList.get(graphNode);
        for (int n : neighbors) {
            if (!visited.contains(n)) {
                topoSortDfs(n, adjList, order, visited, exploring);
            }
        }

        exploring.remove(graphNode);
        order.addFirst(graphNode);
    }

    public static void topoSort(Map<Integer,List<Integer>> adjList){
        LinkedList<Integer> order = new LinkedList<>();
        Set<Integer> visited=new HashSet<>();

        for(int n:adjList.keySet()){
            if(!visited.contains(n))
                topoSortDfs(n,adjList, order, visited, new HashSet<>());
        }

        System.out.println(order);
    }

    public static void main(String[] args){
        List<Integer> empty = new ArrayList<>();
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        adjList.put(6,Arrays.asList(3,1,4));
        adjList.put(1,empty);
        adjList.put(3,Arrays.asList(4));
        adjList.put(5,Arrays.asList(2));
        adjList.put(2,empty);
        adjList.put(4,empty);
        adjList.put(7,empty);

        topoSort(adjList);


        // Create a graph given in the above diagram
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        topoSort(g.adjList);
    }
}
