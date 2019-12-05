import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rupalph on 10/24/19.
 */
public class PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSumHelper(root, sum, result, new LinkedList<>() );
        return result;
    }
    private void pathSumHelper(TreeNode root, int sum, List<List<Integer>> result,LinkedList<Integer> current ) {

        if(root==null) return;
        current.add(root.val);
        if(root.left==null && root.right == null && sum - root.val == 0){
            //System.out.println(current);
            List<Integer> list = new ArrayList<>(current);
            result.add(list);
        }
        pathSumHelper(root.left,sum-root.val,result,current);
        pathSumHelper(root.right,sum-root.val,result,current);
        current.removeLast();
    }

    static  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
