package ik;

/**
 * Created by rupalph on 9/18/19.
 */
public class BST {

    private static class BSTNode {
        int val;
        BSTNode left;
        BSTNode right;

        public BSTNode(int v) {
            val = v;
        }
    }

    public static boolean successor(BSTNode root, int v){

        if(root==null)
            return false;

        BSTNode successor = root;

        if(root.val == v && root.right!=null){
            BSTNode n = min(root.right);
            System.out.println("successor of "+v+" is "+n.val);
            return false;
        }
        else if(v<root.val) {
            if(successor(root.left, v)){
                System.out.println("successor of "+v+" is "+root.val);
                return false;
            }
        }
        else if(v>root.val){
            if(successor(root.right, v)) {
//                System.out.println("successor of "+v+" is "+root.val);
                return true;
            }
        }
        else if(root.val == v)
            return true;
        return false;

    }

    public static BSTNode successor2(BSTNode root, int v){

        if(root==null)
            return null;

        if(root.val == v && root.right!=null){
            BSTNode n = min(root.right);
            System.out.println("successor of "+v+" is "+n.val);
            return n;
        }
        else if(v>root.val){
            return successor2(root.right,v);

        }
        else if(v<root.val) {
            BSTNode n= successor2(root.left, v);
            if(n!=null && n.val == v) {
                System.out.println("successor of "+v+" is "+root.val);
                return root;
            }
            else
                return n;
        }
        else if(root.val == v)
            return root;
        return null;

    }

    private static BSTNode min(BSTNode root) {

        while(root.left!=null) root = root.left;

        return root;
    }

    private static BSTNode insert(BSTNode root, int v) {

        if(root==null)
            return new BSTNode(v);
        if(v<root.val)
            root.left = insert(root.left,v);
        if(v>root.val)
            root.right = insert(root.right,v);
        return root;

    }

    private static void print(BSTNode root) {

        if(root==null)
            return;
        print(root.left);
        System.out.print(root.val+" ");
        print(root.right);

    }

    public static void main(String[] args){
        BSTNode root = insert(null, 25);
        insert(root, 10);
        insert(root, 5);
        insert(root, 15);
        insert(root, 20);
        insert(root, 30);
        insert(root, 27);
        insert(root, 35);

        print(root);

        System.out.println();
        successor2(root, 5);
        successor2(root, 10);
        successor2(root, 20);
        successor2(root, 25);
        successor2(root, 27);
        successor2(root, 30);
        successor2(root, 35);


//        System.out.println("\n"+n.val);
    }
}
