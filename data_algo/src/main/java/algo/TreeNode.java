package algo;

public class TreeNode {
	TreeNode left=null;
	TreeNode right=null;
	int data;
	public TreeNode(int val){
		data=val;
	}
	public TreeNode(int val,TreeNode l,TreeNode r){
		right=r;
		left=l;
		data=val;
	}

	
}
