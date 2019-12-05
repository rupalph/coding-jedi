package com.code.samples;
import java.util.LinkedList;
import java.util.Stack;

import org.junit.Test;


public class BinaryTree {

	
	class Node
	{
		int data;
		Node left,right;
	}

	//inorder traversal without recursion, with stack
	
	public void inorder(Node root)
	{
		Stack<Node> s=new Stack<Node>();
		
		s.push(root);
		
		Node cur=root;
		
		while(!s.isEmpty())
		{
			if(cur.left!=null) {
				cur=cur.left;
				s.push(cur);
			}
			else
			{
				cur=s.pop();
				System.out.print(cur.data+" ");
				
				if(cur.right!=null) {
					cur=cur.right;
					s.push(cur);
				}
			}
			
		}
		System.out.println();
		
	}


	//insert node in binary search tree, left < root and right > root
	public void insert(Node node,int n)
	{
		
		Node newNode=new Node();
		newNode.data=n;
		newNode.left=newNode.right=null;
		if(node!=null)
		{
			System.out.println("insert "+node.data);
			if(n<node.data) {
				if( node.left!=null)
					insert(node.left,n);
				else
					node.left=newNode;
			}
			else
			{
				if(node.right!=null)
					insert(node,n);
				else
					node.right=newNode;
			}
		}
		
		
	}
	
	@Test
	public void testInorder()
	{
		BinaryTree bt=new BinaryTree();
		
		Node root=new Node();
		root.left=root.right=null;
		root.data=10;
		
		bt.insert(root, 3);
		
		bt.insert(root, 15);
		
		bt.insert(root, 7);
		
		bt.inorder(root);
		
	}
	
	//find height of binary tree
	public int findHeight(Node n,int height)
	{
		if(n==null)
			return 0;
		int heightleft=height;
		int heightright=height;
		if(n.left!=null)
			heightleft=findHeight(n.left,(height+1));
		else
			return height+1;
		if(n.right!=null) {
			heightright=findHeight(n.right,(height+1)); }
		if(heightleft>heightright) return heightleft;
		else 
			return heightright;
	}
	
	//mirror of binary tree
	//recursive
	public Node findMirror(Node root)
	{
	
		    if(root == null || (root.left == null && root.right == null))
		        return root;

		    //swap right with left node
		    Node temp = root.left;
		    root.left = root.right;
		    root.right = temp;

		    findMirror(root.left);
		    findMirror(root.right);


		    return root;

		}
	
	//iterative --USING STACK
	public static Node mirrorBinaryTreeIterative(Node	 root){
	    if(root == null || (root.left == null && root.right == null))
	        return root;

	    Node parent = root;
	    Stack<Node> treeStack = new Stack<Node>();
	    treeStack.push(root);

	    while(!treeStack.empty()){
	        parent = treeStack.pop();

	        //swap left with right branch
	        Node temp = parent.right;
	        parent.right = parent.left;
	        parent.left = temp;

	        if(parent.right != null)
	            treeStack.push(parent.right);
	        if(parent.left != null)
	            treeStack.push(parent.left);
	    }
	    return root;
	}
}
