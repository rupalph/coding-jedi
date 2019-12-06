package ik;

/**
 * Created by rupalph on 10/12/19.
 */
public class Node {
    int key;

    Node left;
    Node right;

    public Node(Integer a) {
        key = a;
    }

    public static Node buildTree(Integer[] a, int n) {
        Node root = new Node(a[0]);
        int j =0;
        while(j<n){
            insert(root, a[j], a[2*j+1], a[2*j+2]);
            j++;
        }
        return root;
    }

    private static boolean insert(Node root, Integer a, Integer b, Integer c) {
        if(root==null) return false;
        if(a==null) return false;
        if(root.key==a){
            root.left = b!=null?new Node(b):null;
            root.right = c!=null?new Node(c):null;
            return true;
        }
        if(!insert(root.left,a,b,c))
            return insert(root.right,a,b,c);

        return false;
    }

    public static void print(Node root){
        if(root==null) return;
        print(root.left);
        System.out.print(root.key+" ");
        print(root.right);
    }


}
