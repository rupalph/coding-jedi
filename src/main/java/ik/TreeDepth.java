package ik;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by rupalph on 9/3/19.
 */
public class TreeDepth {

    static class TreeNode {
        int val;
        List<TreeNode> children;
    };

    public int maxDepth(TreeNode root) {

        Queue<TreeNode> list = new LinkedList<>();
        Queue<TreeNode> list2 = new LinkedList<>();

        int maxDepth = -1;
        list.offer(root);

        // TreeNode levelNode = new TreeNode();
        int levelSize = 0;
        while(!list.isEmpty()) {
            levelSize = list.size();

            while(levelSize-->0) {
                TreeNode cur = list.poll();//6

                for(TreeNode child:cur.children) { //7
                    list.offer(child);
                    //levelSize ++ ;
                }
            }
            maxDepth++;

        }

        return maxDepth;
    }





//1 -> 2, 3, 4,
//        3-> 5, 6
//        5-> 7

}
