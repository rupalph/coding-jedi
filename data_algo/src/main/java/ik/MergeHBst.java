package ik;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by rupalph on 10/12/19.
 */
public class MergeHBst {

    public static Node mergeTwoBSTs2(Node root1, Node root2) {
        // Write your code here
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b.compareTo(a));


        getMinMaxHeap(root1,minHeap,maxHeap);
        getMinMaxHeap(root2,minHeap,maxHeap);

        Node n = new Node(minHeap.poll());
        while(!minHeap.isEmpty() || !maxHeap.isEmpty()) {
            while(!maxHeap.isEmpty()) {
                int val = maxHeap.poll();
                insert(n,val);
            }


            while(!minHeap.isEmpty()) {
                int val = minHeap.poll();
                insert(n,val);
            }
        }
        return n;
    }

    public static Node mergeTwoBSTs(Node root1, Node root2) {
        // Write your code here
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();


        getSortedArray(root1,list1);
        getSortedArray(root2,list2);

        List<Integer> list  = merge(list1,list2);
        System.out.println(list);
        Node root = buildTree(list, 0, list.size()-1);
        return root;
    }

    private static Node buildTree(List<Integer> list, int l, int h) {
//        System.out.println(l+" "+h);
        if(l>h) return null;
        int mid = (l+h)/2;
//        System.out.println(mid);

        Node root = new Node(list.get(mid));
        root.left=buildTree(list,l,mid-1);
        root.right = buildTree(list,mid+1, h);
        return root;
    }

    private static List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> list = new ArrayList<>();
        int i=0,j=0;
        for(;i<list1.size()&&j<list2.size();){

            if(list1.get(i)<list2.get(j))
                list.add(list1.get(i++));
            else
                list.add(list2.get(j++));
        }
        for(;i<list1.size();i++)
            list.add(list1.get(i));
        for(;j<list2.size();j++)
            list.add(list2.get(j));
        return list;
    }

    private static Node insert(Node root, int val){
        if(root==null) {
            Node n = new Node(val);
            return n;
        }
        if(val<=root.key){
            root.left=insert(root.left,val);
        }
        else
            root.right = insert(root.right,val);
        return root;
    }

    public static void getSortedArray(Node root1, List<Integer> list) {
        // Write your code here
        if(root1==null) return;

        getSortedArray(root1.left,list);
        list.add(root1.key);
        getSortedArray(root1.right,list);
    }



    public static void getMinMaxHeap(Node root1, PriorityQueue<Integer> minHeap,PriorityQueue<Integer> maxHeap) {
        // Write your code here
        if(root1==null) return;
        // if(root1!=null){
        addToPQ(root1,minHeap,maxHeap);



        // }
        // if(root2!=null){
        //     addToPQ(root2,minHeap,maxHeap);
        // }

        getMinMaxHeap(root1.left,minHeap,maxHeap);
        getMinMaxHeap(root1.right,minHeap,maxHeap);
    }

    private static void addToPQ(Node root1, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap ){
        //inesert root1.val
        if(maxHeap.size()==0 && minHeap.size()==0) {
            maxHeap.add(root1.key);
            return;
        }
        if(root1.key < maxHeap.peek()) maxHeap.add(root1.key);
        else minHeap.add(root1.key);


        //balance heap
        if(minHeap.size()>maxHeap.size()+1) {
            int val = minHeap.poll();
            maxHeap.add(val);
        }

        if(maxHeap.size()>minHeap.size()+1) {
            int val = maxHeap.poll();
            minHeap.add(val);
        }
    }


    public static void main(String[] args){
        Node root1=Node.buildTree(new Integer[]{2,1,3},1);
        Node root2=Node.buildTree(new Integer[]{7,6,8},1);

        Node root = mergeTwoBSTs(root1,root2);
        Node.print(root);

    }

}
