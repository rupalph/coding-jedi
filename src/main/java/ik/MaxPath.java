package ik;

/**
 * Created by rupalph on 10/11/19.
 */
public class MaxPath {
    private static class Node{
        int val;
        Node left;
        Node right;

        public Node(Integer a) {
            val = a;
        }
    }
    static class Pair{
        int maxPath;
        int maxDepth;

        public Pair(int depth, int path) {
            maxDepth = depth;
            maxPath = path;
        }
    }
    public static Pair maxPath(Node root){

        if(root==null) return new Pair(0,0);
        if(root.left == null && root.right==null) return  new Pair(0,0);

        Pair left = maxPath(root.left);
        Pair right = maxPath(root.right);
        int leftDepth = left.maxDepth;
        if(root.left!=null) leftDepth+=1;

        int rightDepth = right.maxDepth;
        if(root.right!=null) rightDepth+=1;
        int maxDepth = Math.max(leftDepth,rightDepth); //max of left_ptr or right_ptr depth + 1 for root
        //max path so far max of leftdepth + rightdepth, left_ptr max path , right_ptr maxpath
        int maxPath = Math.max(leftDepth+rightDepth,Math.max(left.maxPath,right.maxPath));


        return new Pair(maxDepth,maxPath);
    }
    public static void main(String[] args){

        Node n = buildTree(new Integer[]{1,2,3,4,5,null,null,6,null,7,null,-1},5);
        Pair p = maxPath(n);
        System.out.println(p.maxPath);
    }

    private static Node buildTree(Integer[] a, int n) {
        Node root = new Node(a[0]);
        int j =0;
        while(j<5){
            insert(root, a[j], a[2*j+1], a[2*j+2]);
            j++;
        }
        return root;
    }

    private static boolean insert(Node root,Integer a, Integer b, Integer c) {
        if(root==null) return false;
        if(a==null) return false;
        if(root.val==a){
            root.left = b!=null?new Node(b):null;
            root.right = c!=null?new Node(c):null;
            return true;
        }
        if(!insert(root.left,a,b,c))
            return insert(root.right,a,b,c);

        return false;
    }
}
