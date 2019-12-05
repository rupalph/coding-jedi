package com.code.samples;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DirectedGraph {

	/**Design a class (or classes) for representing a directed graph.

Implement a method for finding whether it contains any directed cycles 
(you don't need to identify the cycle, just return true 
if it contains at least one directed cycle and false if it doesn't).

Please specify the headers for each class you need and 
provide a detailed implementation for the doesContainDirectedCycles method. 
*/
	
	class Node {
		public Node(char c, List<Node> l) {
			val=c;
			adjNodes=l;
		}
		char val;
		List<Node> adjNodes; //from current node to next node.
		boolean visited;
		
	}
	
	// a->b,d;b->c,e;c->a
	
	// a->b->c->a 
	
	//do dfs search
	
	public void findCycle(Node start,Node next,String cycle)
	{
		//System.out.println(start.val+"->"+next.val);

		if(next.visited) {
			System.out.println(cycle+" "+start.val+"->"+next.val);
			return;
		}
		//else if(start.visited) return false;
			
		

		next.visited=true;
		if(next.adjNodes!=null)
		{
			for(Node n:next.adjNodes) {
				//System.out.println(n.val);
				findCycle(next,n,(cycle+" "+start.val+"->"+next.val));
				//	return ;
			}
			
		}
		//start.visited=false;
		//return false;
		
	}

	
	@Test
	public void testFindCycle()
	{
		List<Node> l=null;
		Node d=new Node('d',l);
		
		Node e=new Node('e',l);
		
		Node b=new Node('b',l);

		Node a=new Node('a',l);
		Node c=new Node('c',l);

		
		List<Node> l3=new ArrayList<Node>();
		l3.add(b);
		l3.add(d);
		a.adjNodes=l3;
		
		List<Node> l1=new ArrayList<Node>();
		l1.add(e);
		l1.add(c);
		b.adjNodes=l1;
		
		List<Node> l2=new ArrayList<Node>();
		l2.add(a);
		c.adjNodes=l2;
		
		
		findCycle(a,a,"");
	}
	
	//dfs search
	
	//bfs search
	
	
	
}
