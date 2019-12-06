package com.code.samples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class TreeProblems {

	/**The problem to print your tree of friends on facebook has two levels:

    We don't want to interview people that don't know what's a tree or does depth instead of breadth
    We let people that ignores the loops (I'm a friend of my friends) because that can be seen as a bug **
    */
	
	/*Setup:
		Assume primitive Facebook. FB has Members.*/

		class Member {
		public Member(String n, List l) {
			name=n;
			friends=l;
		}
		String name;
		//String email;
		List<Member> friends;
		int level;
		}

		//Question: Code printSocialGraph(Member m). Direct friends of m are Level 1 friends. Friends of friends are level 2 friends.....and so on Print level 1 friends first. Then print level 2 friends....and so on

		void printSocialGraph (Member m){
		//Your code here
			//breadth first search
		/*	Queue<Member> q=new LinkedList<Member>();
			int level=0;
			
			q.add(m);
			List l=null;
			Member levelNode=new Member("level",l);
			while(q.peek()!=null)
			{
				
				Member temp=q.remove();
				if(temp.name.equals("level")) {
					System.out.println();
					level++;
				}
				else
					System.out.print(temp.name+" ");
				if(temp.friends!=null) {
					q.add(levelNode);
					q.addAll(temp.friends);
				
				}
				
			}
			*/
		
			
			Queue<Member> q1=new LinkedList<Member>();
			Queue<Member> q2=new LinkedList<Member>();
			q1.add(m);
			
			for(int i=0;i<20;i++)
			{
				while(q1.peek()!=null)
				{
					Member t=q1.remove();
					System.out.print(t.name);
					if(t.friends!=null)
						q2.addAll(t.friends);			
				}
				System.out.println();
				boolean res=q2.removeAll(q1);
			}
		}

		@Test
		public void testPrintSocialGraph()
		{
			Member m =buildFriends();
			printSocialGraph(m);
		}
		
		/**riya
		|
		amy, emily
		|
		pam, yan*/


		private Member buildFriends() {
			List l=null;
			Member m4=new Member("pam",l);
			Member m5=new Member("yan",l);
			
			ArrayList<Member> f2=new ArrayList();
			f2.add(m4);
			f2.add(m5);
			
			Member m2=new Member("amy",f2);

			Member m3=new Member("emily",l);
			ArrayList<Member> f1=new ArrayList();
			f1.add(m2);
			f1.add(m3);
			Member m1=new Member("riya",f1);
			
			
			

			return m1;
			
			
		}
		
		
		//Given a pre and post order kindof a traversal (2 arrays) create an n-ary treee out of it with struct of the form :
		class Node {
			public Node(int i) {
				data=i;
			}
			int data;
			List<Node> children;
		}
			
		public  Node constructTree(int[] preorder,int preStart,int preEnd,int[] postorder,int postStart,int postEnd)
		{
			if(preStart>preEnd) return null;
			Node root=new Node(preorder[preStart]);
			int preCur=preStart+1;
			int postCur=postStart;
			while(preCur<=preEnd&&postCur<=postEnd-1)
			{
				//Node child=new Node(preorder[preCur]);
				int length=0;
				while(postorder[postCur]!=preorder[preCur])
				{
					postCur++;
					length++;
				}
				//postorder[postCur]==preorder[preCur]
				Node child=constructTree(preorder,preCur,preCur+length,postorder,postCur-length,postCur);
				root.children.add(child);
				postCur++;
				preCur+=length+1;
			}
			return root;
		
		}
		
		@Test
		public void testConstructTree()
		{
			//
		}
}