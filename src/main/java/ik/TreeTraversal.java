package ik;

import java.util.*;

/**
 * Created by rupalph on 9/13/19.
 */
public class TreeTraversal {

    private static class TreeNode {
        public int val;

        public TreeNode(int val, TreeNode left_ptr, TreeNode right_ptr) {
            this.val = val;
            this.left_ptr = left_ptr;
            this.right_ptr = right_ptr;
        }

        TreeNode left_ptr;
        TreeNode right_ptr;
    }

    static int[] postorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();

        st.push(root);
        LinkedList<TreeNode> visited = new LinkedList<>();
        while (!st.isEmpty()) {
            TreeNode cur = st.peek();
            while(cur.right_ptr!=null && !isVisisted(visited, cur)) {
                while (cur.left_ptr != null && !isVisisted(visited, cur)) {
                    if (cur.right_ptr != null)
                        st.push(cur.right_ptr);
                    st.push(cur.left_ptr);
                    cur = cur.left_ptr;
                }

                if (cur.right_ptr != null && !isVisisted(visited, cur)) {
                    st.push(cur.right_ptr);
                    cur = cur.right_ptr;
                }
            }

            TreeNode top = st.pop();
            visited.add(top);
            System.out.println(top.val);
        }
        int[] result = new int[visited.size()];
        int i = 0;
        for(TreeNode n:visited) {
            result[i++] = n.val;
        }
        return result;
    }

    static int[] postorderTraversal2(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();

        st.push(root);
        LinkedList<TreeNode> visited = new LinkedList<>();
        while (!st.isEmpty()) {
            TreeNode cur = st.peek();
            if((cur.left_ptr==null&&cur.right_ptr==null) || isVisisted(visited, cur)){
                st.pop();
                visited.add(cur);
                System.out.println(cur.val);
                cur = !st.isEmpty()?st.peek():null;

            }
            while (cur!=null && !isVisisted(visited, cur)) {
                if (cur.right_ptr != null)
                    st.push(cur.right_ptr);
                if(cur.left_ptr!=null) {
                    st.push(cur.left_ptr);
                    cur = cur.left_ptr;
                }
              else
                  cur = cur.right_ptr;
            }

//                if (cur.right_ptr != null && !isVisisted(visited, cur)) {
//                    st.push(cur.right_ptr);
//                    cur = cur.right_ptr;
//                }
//
//            if(!isVisisted(visited, cur.left_ptr)) {
//                TreeNode top = st.pop();
//                visited.add(top);
//                System.out.println(top.val);
//            }
        }
        int[] result = new int[visited.size()];
        int i = 0;
        for(TreeNode n:visited) {
            result[i++] = n.val;
        }
        return result;
    }

    private static boolean isVisisted(LinkedList<TreeNode> visited, TreeNode cur){

        if(!visited.isEmpty())
                if(visited.peekLast()==cur.right_ptr)
                    return true;
                else if(visited.peekLast()==cur.left_ptr)
                    return true;
        return false;
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(6, null, null);
        TreeNode b = new TreeNode(7, null, null);

        TreeNode c = new TreeNode(5, a, b);
        TreeNode d = new TreeNode(4, null, null);

        TreeNode e = new TreeNode(3, null, null);

        TreeNode f = new TreeNode(2, d, c);

        TreeNode g = new TreeNode(1, f, e);

        int[] res = postorderTraversal2(g);

        System.out.println(Arrays.toString(res));

    }
}