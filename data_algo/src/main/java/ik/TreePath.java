package ik;

import java.util.*;

/**
 * Created by rupalph on 8/30/19.
 */
public class TreePath {
    private static class TreeNode {
        int val;
        TreeNode left_ptr;
        TreeNode right_ptr;
        public TreeNode(int v, TreeNode left, TreeNode right){
            val = v;
            left_ptr = left;
            right_ptr = right;
        }
    }

    /*
    Complete the function below
*/
    static void printAllPaths(TreeNode root){

        printAll(root, "");

    }

    private static void printAll(TreeNode root, String path){
        if(root==null) return;
        if(root.left_ptr == null && root.right_ptr == null){
            System.out.println(path+" "+root.val);
            return;
        }

        printAll(root.left_ptr, path+" "+root.val);
        printAll(root.right_ptr, path+" "+root.val);
    }


    public static void main(String[] args){
//        TreeNode n4 =  new TreeNode(4,null, null);
//        TreeNode n5 = new TreeNode( 5, null, null);
//        TreeNode n2 =  new TreeNode(2,null, null);
//        TreeNode n3 = new TreeNode( 3, n4, n5);
//        TreeNode n1 = new TreeNode( 1, n2, n3);
//
//        printAllPaths(n1);

//        TreeNode n5 = new TreeNode( 5, null, null);
//        TreeNode n4 =  new TreeNode(4,null, n5);
//        TreeNode n3 = new TreeNode( 3, null, n4);
//        TreeNode n2 =  new TreeNode(2,null, n3);
//        TreeNode n1 = new TreeNode( 1, null, n2);
//
//        printAllPaths(n1);


//        TreeNode n5 = new TreeNode( 5, null, null);
//        TreeNode n4 =  new TreeNode(4,null, n5);
//        TreeNode n3 = new TreeNode( 3, null, n4);
//        TreeNode n2 =  new TreeNode(2,null, n3);
//
//        TreeNode n8 = new TreeNode( 8, null, null);
//        TreeNode n7 =  new TreeNode(7,null, n8);
//        TreeNode n6 = new TreeNode( 6, null, n7);
//        TreeNode n1 = new TreeNode( 1, n6, n2);

//        printAllPaths(n1);

        TreeNode n8 = new TreeNode( 8, null, null);
        TreeNode n9 = new TreeNode( 9, null, null);

        TreeNode n5 = new TreeNode( 5, n8, n9);
        TreeNode n4 =  new TreeNode(4,null, null);
        TreeNode n2 =  new TreeNode(2,n4, n5);

        TreeNode n6 = new TreeNode( 6, null, null);
        TreeNode n7 =  new TreeNode(7,null, null);

        TreeNode n3 = new TreeNode( 3, n6, n7);
        TreeNode n1 = new TreeNode( 1, n2, n3);

//        findLca(n1, 4, 9);

//        int res2 = findLca3(n1, 8, 9);
//        System.out.println(res2);

        int res3 = kth_smallest_element(n1, 9);
        System.out.println(res3);
    }

    static int findLca(TreeNode n, int a, int b){
        List<Integer> re = new ArrayList<>();
        boolean res = findLca(n, 4, 9, new HashSet<>(), re);
        System.out.println(re);
        if(re.isEmpty())
            return -1;
        return re.get(0);

    }
    static boolean findLca(TreeNode n, int a, int b, Set<Integer> nodeFound, List<Integer> result ){
        if(n==null) return false;
        if(n.val == a||n.val == b) {
            nodeFound.add(n.val);
            return true;
        }
        boolean res1=findLca(n.left_ptr, a, b, nodeFound, result);
        boolean res2 =findLca(n.right_ptr, a, b, nodeFound, result);
        if(res1 && nodeFound.contains(a)
            && res2  && nodeFound.contains(b)) {
            result.add(n.val);
            return true;
        }
        return res1||res2;
    }

    static boolean findLca(TreeNode n, int a, int b, LinkedList<Integer> path ){
        if(n==null) return false;
        if(n.val == a||n.val == b) {

            return true;
        }
        path.add(n.val);
        boolean res1=findLca(n.left_ptr, a, b, path);
        boolean res2 =findLca(n.right_ptr, a, b, path);
        if(res1 &&
                 res2  ) {
            return true;
        }
        path.pop();
        return res1||res2;
    }

    static int findLca3(TreeNode n, int a, int b){
        if(n==null) return -1;
        if(n.val == a||n.val == b) {

            return n.val;
        }
//        path.add(n.val);
        int res1=findLca3(n.left_ptr, a, b);
        int res2 =findLca3(n.right_ptr, a, b);
        if(res1!=-1 &&
                res2!=-1  ) {
            return n.val;
        }
//        path.pop();
        return res1==-1?res2:res1;
    }

    static int kth_smallest_element(TreeNode root, int k) {

        LinkedList<TreeNode> st = new LinkedList<>();

        st.push(root);
        TreeNode tmp = root;

        while(!st.isEmpty()) {

            while(tmp!=null && tmp.left_ptr!=null) {
                st.push(tmp.left_ptr);
                tmp = tmp.left_ptr;
            }

            TreeNode top = st.pop();
            System.out.println(top.val);
            k--;
            if(k==0)
                return top.val;
            if(top.right_ptr!=null)
                st.push(top.right_ptr);
            tmp = top.right_ptr;
        }
        return -1;
    }

    //WIP
    static int kth_smallest_element2(TreeNode root, int k, int count) {

        if(root==null) return count;
        if(count==k)
            return root.val;
        int val= kth_smallest_element2(root.left_ptr, k, count+1);
        val = kth_smallest_element(root, count+val);
            val = kth_smallest_element2(root.right_ptr, k, count+val+1);

        return val;
    }


}
