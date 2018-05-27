import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class TreeProblems {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> map=new HashSet<>();

        return findTarget(root,k,map);
    }

    public boolean findTarget(TreeNode root, int k,Set<Integer> set) {
        if(root==null)
            return false;
        if(set.contains(k-root.val))
            return true;
        set.add(root.val);
        return findTarget(root.left,k,set) || findTarget(root.right,k,set);
    }

    // Given a binary tree where each node has a numeric value, write a program to print all paths that add up to a given sum. Note that this path can be from any node to any other node following the links in the tree.
// Time: 45 mins.
// Points checked: coding of basic solution, thinking through the LinkedList solution and at least giving a pseudo-code. Time and space complexity analysis.
    public static int findPathSum(TreeNode root, int sum, int sumSoFar, List<Integer> list){
//        if(root!=null)System.out.println(root.val+","+list.toString()+","+sumSoFar);

        if(sumSoFar == sum && !list.isEmpty()) {
            System.out.println("******"+list.toString());
            list.clear();
        }
        if(root==null)
            return sumSoFar;

        else if(root.val == sum) {
            System.out.println(root.val);
            findPathSum(root.left, sum, 0, list);
            findPathSum(root.right,sum, 0, list);
        }
        else if(root.val<= sum-sumSoFar){
            list.add(root.val);
            sumSoFar += root.val;
            sumSoFar = findPathSum(root.left, sum, sumSoFar, list);
            sumSoFar = findPathSum(root.right, sum, sumSoFar, list);
        }
        else {
            findPathSum(root.left, sum, 0, new LinkedList<>());
            findPathSum(root.right, sum, 0, new LinkedList<>());
        }

        return sumSoFar;
    }


    public static boolean isBst(TreeNode root, int min, int max){
        if(root==null)
            return true;
        if(root.val >=min && root.val <= max) {
            if(root!=null)
                System.out.println(root.val+" "+min+" "+max);
            if(root.left ==null && root.right == null)
                return true;
            else if (root.left == null && root.right != null && root.right.val >= root.val)
                return isBst(root.right, root.val, max);
            else if (root.right == null && root.left != null && root.left.val <= root.val)
                return isBst(root.left, min, root.val);
            else if (root.left.val <= root.val && root.right.val >= root.val) {
                boolean result1 = isBst(root.left, min, root.val);
                boolean result2 = isBst(root.right, root.val, max);
                return result1 && result2;
            }
            else
                return false;
        }
        else
            return false;
    }

    public static TreeNode findMax(TreeNode root, TreeNode curMax, int attribVal) {
        if(root==null || (root.left==null && root.right == null))
            return curMax;
        if(root.left != null && root.right != null && root.left.val == attribVal && root.right.val == attribVal){
            findMax(root.left, curMax, attribVal);
            findMax(root.right, curMax, attribVal);
        }
        else {
            if(root.left!=null)
                findMax(root.left, root.left, attribVal);
            if(root.right!=null)
                findMax(root.right, root.right, attribVal);
        }
        return curMax;
    }
    public static void  mirror(TreeNode root) {
        if(root==null)
            return ;
        TreeNode left= root.left;
        TreeNode right = root.right;
        TreeNode tmp = left;
        root.left = right;
        root.right = tmp;

        mirror(left);
        mirror(right);
    }

//10
//        /\
//        5 7
//        /\ \
//        -3 -2 2 sum=0
//
//            5, 9, 0, []
//            3, 9, 5, [5]
//            null, 9, 8, [5,3]
//            1, 9, 8, [5,3]
//            null, 9, 9, [5,3,1]
//
//            7, 9, 0, []
//            2, 9, 7, [7]
//            null, 9, 9, [7,2]

    public static void main(String[] args){
        TreeNode three=new TreeNode(3);
        TreeNode one=new TreeNode(1);
        TreeNode two=new TreeNode(2);
        TreeNode five=new TreeNode(5, three, one);

        TreeNode seven=new TreeNode(7, null, two);
        TreeNode ten=new TreeNode(10, five, seven);
//
//        findPathSum(ten, 9, 0, new LinkedList<>());
//
//
        TreeNode left1=new TreeNode(10);
        TreeNode left2=new TreeNode(18);
        TreeNode left=new TreeNode(15, left1, left2);
        TreeNode right1=new TreeNode(60);
        TreeNode right2=new TreeNode(40);
        TreeNode right=new TreeNode(50, right2, right1);

        TreeNode root=new TreeNode(30, left, right);
//
//        System.out.println(isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

//        TreeNode left1=new TreeNode(3);
//        TreeNode left2=new TreeNode(3);
//        TreeNode left=new TreeNode(3, left1, left2);
//        TreeNode right1=new TreeNode(1);
////        TreeNode right2=new TreeNode(2);
//        TreeNode right=new TreeNode(1, null, right1);
//
//        TreeNode root=new TreeNode(2, left, right);

//        System.out.println(findMax(root, root, root.val).val);
//        TreeNode root = ten;
        System.out.println(root);

        mirror(root);

        System.out.println(root);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
    TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;this.left=left;this.right=right;
    }

    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(val+"|");
        toString(this,sb);
        return sb.toString();
    }

    void toString(TreeNode cur,StringBuilder sb){
        if(cur.left!=null)
            sb.append(" "+cur.left.val+" ");
        if(cur.right!=null)
            sb.append(" "+cur.right.val+" ");
        sb.append("|");

        if(cur.left!=null)toString(cur.left,sb);
        if(cur.right!=null)toString(cur.right,sb);


    }
}
