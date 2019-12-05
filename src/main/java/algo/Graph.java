package algo;
// This is the text editor interface. 
// Anything you type or change here will be seen by the other person in real time.

//acyclic directed graph
import java.util.*;

class Node{
	int val;
	List<Node> neighbours=new ArrayList<>();
	public Node(int val,List<Node> neighbours)
	{
		this.val=val;
		this.neighbours=neighbours;
	}
	public Node(Node n)
	{
		this.val=n.val;

	}
	public void addNeighbor(Node n){
		neighbours.add(n);
	}
}
public class Graph {
	Node graphCopy(Node root) {
		LinkedList<Node> queue = new LinkedList<>();
		Map<Node,Node> map=new HashMap<>();
		queue.push(root);
		boolean isRoot=true;
		Node newRoot=null;
		while(!queue.isEmpty()) {
			Node cur=queue.pop();
			Node newNode = new Node(cur); //1-> 2,3 3->4,5
			if(map.get(cur)!=null)
			{
				Node parent=map.get(cur);
				parent.addNeighbor(newNode);
				map.remove(cur);
			}
			if(cur.neighbours!=null) {
				for(Node neighbor:cur.neighbours)
				{
					//System.out.println(neighbor.val);
					if(map.get(neighbor)==null)
						map.put(neighbor,newNode);

					queue.add(neighbor);
				}
			}
			if(isRoot) {
				newRoot = newNode;
				isRoot = false;
			}
		}
		return newRoot;
	}
	public void print(Node node)
	{
		if(node!=null) {
			System.out.print(node.val + "->");
			if(node.neighbours!=null) {
				for(Node n:node.neighbours){
					print(n);
				}
			}
		}
	}
	public static void main(String[] args){
		Node n1=new Node(2,null);


		List<Node> list2=new ArrayList<>();
		Node n3=new Node(4,null);
		Node n4=new Node(5,null);
		list2.add(n3);
		list2.add(n4);
		Node n2=new Node(3,list2);
		List<Node> list=new ArrayList<>();
		list.add(n1);
		list.add(n2);
		Node root =new Node(1,list);
		Graph g=new Graph();
		Node newRoot=g.graphCopy(root);
		g.print(newRoot);   
	}
}