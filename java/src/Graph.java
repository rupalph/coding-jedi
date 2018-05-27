import java.util.*;

/**
 * Created by rupalh on 5/21/18.
 */
public class Graph {

    List<GraphNode> graphNodeList = new ArrayList<>();
    LinkedHashSet<Integer> stack = new LinkedHashSet<>();

    Graph(List list) {
        this.graphNodeList = list;
    }

    public void dfs() {
        System.out.println("\n\n---dfs----");

        for (GraphNode node : graphNodeList) {
            dfsVisit(node);
        }
    }

    private void dfsVisit(GraphNode node) {
        if (node.visited && !node.completed)
            System.out.println("cycle");
        if (!node.visited) {
            node.visited = true;
            for (GraphNode nextNode : node.adjNodes) {
                dfsVisit(nextNode);
            }
        }
        if (!stack.contains(node.v)) {
            System.out.println("visit " + node.v + " done");
            node.completed = true;
            stack.add(node.v);
        }
    }

    public void bfs() {
        System.out.println("\n\n---bfs----");

        for (GraphNode node : graphNodeList) {
            bfsVisit(node);
        }
    }

    public void bfsVisit(GraphNode g) {

        Queue<GraphNode> q = new LinkedList<>();
        q.offer(g);
        while (!q.isEmpty()) {
            GraphNode cur = q.poll();
//            if (cur.visited) {
                System.out.println(cur.v);
//            }

            for (GraphNode n : cur.adjNodes) {
                if (!n.visited) {
                    q.offer(n);
                    n.visited = true;
                }
            }

        }
    }

    public static void main(String[] args) {
        GraphNode n0 = new GraphNode(0, new ArrayList<>());
        GraphNode n1 = new GraphNode(1, new ArrayList<>());
        GraphNode n3 = new GraphNode(3, Arrays.asList(n1));
        GraphNode n2 = new GraphNode(2, Arrays.asList(n3));
        GraphNode n4 = new GraphNode(4, Arrays.asList(n0, n1));
        GraphNode n5 = new GraphNode(5, Arrays.asList(n0, n2, n4));
        Graph g = new Graph(Arrays.asList(n0, n1, n2, n3, n4, n5));
//        g.dfs();
        g.bfsVisit(n5);
        GraphNode m2 = new GraphNode(3, null);
        GraphNode m1 = new GraphNode(1, Arrays.asList(m2));
        GraphNode m0 = new GraphNode(0, Arrays.asList(m1));
        m2.adjNodes = Arrays.asList(m0);

        Graph g2 = new Graph(Arrays.asList(m0, m1, m2));
//        g2.dfs();
        g2.bfs();
    }
}

class GraphNode {
    int v;
    boolean visited, completed;
    List<GraphNode> adjNodes = new ArrayList<>();

    GraphNode(int i, List<GraphNode> list) {
        this.v = i;
        this.adjNodes = list;
    }
}