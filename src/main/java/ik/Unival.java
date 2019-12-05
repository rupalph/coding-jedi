package ik;

/**
 * Created by rupalph on 9/13/19.
 */
public class Unival {


        private static class TreeNode{
            public int val;

            public TreeNode(int val, TreeNode left_ptr, TreeNode right_ptr) {
                this.val = val;
                this.left_ptr = left_ptr;
                this.right_ptr = right_ptr;
            }

            public TreeNode left_ptr;
            public TreeNode right_ptr;
        }

        private static class Tuple {
            boolean isUnival;
            int univalCountSoFar;
            public Tuple(boolean is, int count){
                isUnival = is;
                univalCountSoFar = count;
            }
        }


    /*
        Complete the function below
    */
    static int findSingleValueTrees(TreeNode root){

        Tuple res = isUnivalHelper(root, 0);
        return res.univalCountSoFar;
    }

    static Tuple isUnivalHelper(TreeNode root, int count){
        if(root==null) return new Tuple(true, count);
        if(root.left_ptr == null && root.right_ptr == null)
            return new Tuple(true, count +1);

        Tuple leftUnival= isUnivalHelper(root.left_ptr, count);
        Tuple rightUnival = isUnivalHelper(root.right_ptr, leftUnival.univalCountSoFar);

        if(isUnival(root) && leftUnival.isUnival && rightUnival.isUnival)
                return new Tuple(true, rightUnival.univalCountSoFar + 1);
        return new Tuple(false, rightUnival.univalCountSoFar);
    }

    private static boolean isUnival(TreeNode root) {
        if(root.left_ptr!=null && root.right_ptr!=null && root.left_ptr.val==root.right_ptr.val && root.val == root.left_ptr.val)
            return true;
        else if(root.left_ptr == null && root.right_ptr!=null && root.right_ptr.val == root.val)
            return true;
        else if(root.right_ptr == null && root.left_ptr!=null && root.left_ptr.val == root.val)
            return true;
        return false;
    }

    public static void main(String[] args){
//        TreeNode a = new TreeNode(2, null, null);
//        TreeNode b = new TreeNode(2, null, null);
//
//        TreeNode c = new TreeNode(2, a, b);
//        TreeNode d = new TreeNode(2, null, null);
//
//        TreeNode e = new TreeNode(5, null, null);
//
//        TreeNode f = new TreeNode(3, d, c);
//
//        TreeNode g = new TreeNode(5, e, f);
//
//        int res = findSingleValueTrees(g);
//
//        System.out.println(res);


        TreeNode a = new TreeNode(5, null, null);
        TreeNode b = new TreeNode(5, null, null);

        TreeNode c = new TreeNode(5, a, b);
        TreeNode d = new TreeNode(5, null, null);

        TreeNode e = new TreeNode(5, null, null);

        TreeNode f = new TreeNode(5, null, e);

        TreeNode g = new TreeNode(5, c, f);

        int res = findSingleValueTrees(g);

        System.out.println(res);
    }
}
