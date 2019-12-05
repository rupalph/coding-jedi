package reference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;


public class ExpressionTree {

	/*
	Convert inorder to preorder or postorder

	The Postorder input is: a b + c d e +**

	    Consider first character if it is not symbol then create node add it to stack
	    If character is symbol then create node with symbol pop elements and add to left and right of symbol
	    Push symbol node in to the stack.
	    Repeat 1, 2 and 3 till iterator has no more elements

	**/
	class Tree<Type>
	{
		class TreeNode
		{
			public TreeNode(Character c) {
				left=right=null;
				val=c;
			}
			TreeNode left,right;
			char val;
		}

		TreeNode root=null;
		List<Character> l=new ArrayList<Character>();
		public TreeNode createNode(Character c) {
			TreeNode n=new TreeNode(c);
			
			
			return n;
		}

		
	}
	
	
	class NodeStack<Type>
	{
		// 1-2-3,top=1, push(4) 4-2-1-3 
		// 

		LinkedList<Type> stack=new LinkedList<Type>();
		
		public Type pop() {
			if(stack.isEmpty()) return null;
			return stack.removeFirst();
		}

		public void push(Type node) {
			stack.addFirst(node);
		}
		
		public Type peek() {
			if(stack.isEmpty()) return null;
			return stack.getFirst();
		}
	}
	
	public Tree.TreeNode createExpressionTree(String postOrder){
	    
		
	    Tree tree = new Tree();
	    NodeStack nodeStack = new NodeStack();
	    
	    
	    Tree.TreeNode node;
	    for(char c:postOrder.toCharArray()) {
	       
	        if(!Character.isDigit(c)){
	            node = tree.createNode(c);
	            node.right = (Tree.TreeNode) nodeStack.pop();
	            node.left = (Tree.TreeNode) nodeStack.pop();
	            nodeStack.push(node);
	        }else{
	            node = tree.createNode(c);
	            nodeStack.push(node);
	        }
	    }
	    node = (Tree.TreeNode) nodeStack.pop();
	    return node;
	}

	//1 2 + 3 - 
	//-
	
	//1+2
	
	public String convertPostfix(String exp)
	{
		StringBuffer sb=new StringBuffer();
		NodeStack<Character> st=new NodeStack<Character>();
		boolean bracket=false;
		for(char c:exp.toCharArray())
		{
			
			if(!Character.isDigit(c))
			{
				Character top=st.peek();
				if(c=='(') bracket=true;
				if(c==')') { while(st.peek()!=null && st.peek()!='(') sb.append(st.pop()+" "); bracket=false; st.pop(); continue;}
				if(top!=null&&!bracket&&(c=='+' || c== '-' ) ) while(st.peek()!=null) sb.append(st.pop()+" ");
				st.push(c);
				
			}
			else {
				
				
				sb.append(c+" ");
			}
		}
		while(st.peek()!=null)
		{
			sb.append(st.pop() + " ");
		}
			
		return sb.toString();
	}
	

	@Test
	public void testConvertPosix()
	{
		ExpressionTree et=new ExpressionTree();
		String exp="1+2*3/2-9"; //1232/*+9- .. is this the answer? 1*2+3 12*3+
		String exp1="1*(2+3)"; //1232/*+9- .. is this the answer? 1*2+3 12*3+    
		String res=et.convertPostfix(exp);
		String res2=et.convertPostfix(exp1);
		System.out.println(res);
		System.out.println(res2);

	}
}
