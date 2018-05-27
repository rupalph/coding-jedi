import java.util.*;

class Node {
    int dst,cost, dist;
    public Node(int d, int c, int h){
        this.dist=h;
        this.dst=d;
        this.cost=c;
    }
    public String toString(){
        return this.dst+":"+this.cost+":"+this.dist;
    }
}
public class Flight {


    public void findCheapestPrice(int n, int[][] flights, int src, int dst, int K)     {

        Map<Integer, Integer> costMap = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(n,(a, b)-> a.cost - b.cost);
        queue.offer(new Node(src,0,0));
        int[][] graph = new int[n][n];
        for(int[] node:flights){
            graph[node[0]][node[1]]=node[2];
        }
        System.out.println(Arrays.deepToString(graph));

        while(!queue.isEmpty()) {
//        for(int i=0;i<3;i++){
            Node start = queue.poll();
            int[] neighborCosts = graph[start.dst];
            System.out.println(Arrays.toString(neighborCosts));
            for(int i=0;i<neighborCosts.length;i++){
                int cost = neighborCosts[i];
                if(cost>0){
                    int newCost = start.cost+cost;
                    if(costMap.containsKey(i)){
                        if(newCost < costMap.get(i)) {
                            queue.remove(new Node(i,costMap.get(i), start.dist));
                            costMap.put(i, newCost);
                            queue.offer(new Node(i,newCost, start.dist+1));
                        }
                    }
                    else {
                        queue.offer(new Node(i,newCost, start.dist+1));
                        costMap.put(i,newCost);
                    }

                    System.out.println(queue);

//                    if(dst == i)
//                        break;
                }
//                break;


            }
        }
        System.out.println(costMap.get(dst));
    }

    public static void main(String[] args){
        int flights[][] = {{0,1,100},{1,2,100},{0,2,500}};
        Flight f = new Flight();
        f.findCheapestPrice(3,flights,0,2,1);
    }
}