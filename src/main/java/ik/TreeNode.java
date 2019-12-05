package ik;

/**
 * Created by rupalph on 10/12/19.
 */
public class TreeNode {
    int val;

    TreeNode left_ptr;
    TreeNode right_ptr;

    public TreeNode(Integer a) {
        val = a;
    }

    public static TreeNode buildTree(Integer[] a, int n) {
        TreeNode root = new TreeNode(a[0]);
        int j =0;
        while(j<n){
            insert(root, a[j], a[2*j+1], a[2*j+2]);
            j++;
        }
        return root;
    }

    private static boolean insert(TreeNode root, Integer a, Integer b, Integer c) {
        if(root==null) return false;
        if(a==null) return false;
        if(root.val ==a){
            root.left_ptr = b!=null?new TreeNode(b):null;
            root.right_ptr = c!=null?new TreeNode(c):null;
            return true;
        }
        if(!insert(root.left_ptr,a,b,c))
            return insert(root.right_ptr,a,b,c);

        return false;
    }

    public static void print(TreeNode root){
        if(root==null) return;
        print(root.left_ptr);
        System.out.print(root.val +" ");
        print(root.right_ptr);
    }


}
