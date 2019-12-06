package ik;

import java.util.Stack;

/**
 * Created by rupalph on 10/12/19.
 */
public class BSTIterator {
        /*
        private static class TreeNode{
            public int val;
            public TreeNode left_ptr;
            public TreeNode right_ptr;
        }
    */

    /*
        Complete the function below
    */

    Stack<TreeNode> st;

    public BSTIterator(TreeNode root) {
        st = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            st.push(cur);
            cur = cur.left_ptr;
        }

        print();
    }

    private void print() {
        st.forEach(k -> System.out.println(k.val));
    }

    public boolean hasNext() {
        if (!st.isEmpty()) return true;
        return false;
    }

    public int next() {
        TreeNode cur = st.pop();
        int retVal = cur.val;
        if (cur.right_ptr != null) {
            cur = cur.right_ptr;
            while (cur != null) {
                st.push(cur);
                cur = cur.left_ptr;
            }
        }
        //System.out.println("Next:" + cur.val);
        print();
        return retVal;
    }



    public static void main(String[] args){
        TreeNode root = TreeNode.buildTree(new Integer[]{2,1,3,null,null,null,null}, 3);
        BSTIterator it= new BSTIterator(root);
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }


}
