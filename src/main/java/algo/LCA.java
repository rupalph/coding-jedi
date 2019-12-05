package algo;

public class LCA {
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	    if(root==null)
	        return null;
	 
	    if(root==p || root==q)
	        return root;
	 
	    TreeNode l = lowestCommonAncestor(root.left, p, q);
	    TreeNode r = lowestCommonAncestor(root.right, p, q);
	 
	    if(l!=null&&r!=null){
	        return root;
	    }else if(l==null&&r==null){
	        return null;
	    }else{
	        return l==null?r:l;
	    }
	}
	static class Result {
		boolean foundLeft;
		boolean foundRight;
		TreeNode lca;

		public Result(boolean b, boolean b1) {
			foundLeft = b;
			foundRight = b1;
		}

		public Result(boolean b, boolean b1, TreeNode n) {
			foundLeft = b;
			foundRight = b1;
			lca = n;
		}
	}
	public static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null) return null;
		Result lca = new Result(false,false,null);
		lcaHelper(root,p,q, lca);
		return lca.lca;
	}
	public static Result lcaHelper(TreeNode root, TreeNode p, TreeNode q, Result res){
		if(root.data == p.data) return new Result(true,false);
		if(root.data == q.data) return new Result(false, true);
		if(root.left == null && root.right == null){
			return new Result(false, false);
		}
		boolean foundLeft = false;
		boolean foundRight = false;
		if(root.left!=null){
			Result r = lcaHelper(root.left, p,q, res);
			foundLeft = r.foundRight||r.foundLeft?true:false;
		}
		if(root.right!=null){
			Result r = lcaHelper(root.right, p, q, res);
			foundRight = r.foundRight||r.foundLeft?true:false;
		}
		if(foundLeft && foundRight) {
			res.lca = root;
			return new Result(false,false);
		}
		return new Result(foundLeft,foundRight);
	}
	public static void main(String[] args){
		TreeNode n6=new TreeNode(6);
		TreeNode n7=new TreeNode(7);
		TreeNode n8=new TreeNode(8);
		TreeNode n9=new TreeNode(9);
		TreeNode n10=new TreeNode(10);
		TreeNode n11=new TreeNode(11);
		
		TreeNode n4=new TreeNode(4,n8,n9);
		TreeNode n5=new TreeNode(5,n10,n11);
		TreeNode n3=new TreeNode(3,n6,n7);
		
		TreeNode n2=new TreeNode(2,n4,n5);
		TreeNode root=new TreeNode(1,n2,n3);
		
		TreeNode lca=lowestCommonAncestor(root,n9,n10);
		System.out.println(lca.data);

		TreeNode lca2=lca(root,n9,n10);
		System.out.println(lca2.data);

	}
}
