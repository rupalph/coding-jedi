package ik;

import java.util.*;

/**
 * Created by rupalph on 9/8/19.
 */
public class ReverseEdgeGraph {
     /*
     * Complete the function below.
     */


//        For your reference:

        static class Node
    	{
    		Integer val;
    		List<Node> neighbours = new ArrayList<>();
    		Node(Integer _val)
    		{
    			val = _val;
    			neighbours.clear();
    		}

            public Integer getId() {
                return val;
            }

            public List<Node> getNeighbors() {
                return neighbours;
            }
        }


    static Node build_other_graph(Node node)
    {
        return node;
    }

    void bfs(Node start, Set<Integer> visitedNodes){
        Queue<Node> q = new LinkedList();
        visitedNodes.add(start.getId());
        while(!q.isEmpty()){
            Node u = q.poll();
            for(Node v:u.getNeighbors()) {
                if(!visitedNodes.contains(v.getId())){
                    q.add(v);
                    visitedNodes.add(v.getId());
                }

            }
        }
    }

    void dfs(Node u, Set<Integer> visitedNodes) {
        visitedNodes.add(u.getId());

        for (Node v : u.getNeighbors()) {
            if (!visitedNodes.contains(v.getId())) {
                dfs(v, visitedNodes);
            }
        }
    }
}

