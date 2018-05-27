import com.sun.scenario.effect.Merge;
import sun.jvm.hotspot.oops.Array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * */
   class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
      static ListNode init(int[] a) {
          ListNode x = new ListNode(a[0]);
          ListNode head = x;
          for (int i = 1; i < a.length; i++) {
              x.next = new ListNode(a[i]);
              x = x.next;
          }
          return head;
      }
      public String toString() {
          String s="";
          ListNode x = this;
          while (x != null) {
              s += (x.val + "-");
              x = x.next;
          }
          return s;
      }
      static void print(ListNode x) {

          while(x!=null){
              System.out.print(x.val+"-");
              x=x.next;
          }
          System.out.println();
      }
  }

public class MergeLists {
    public static void main(String[] args){
        MergeLists m=new MergeLists();
//        ListNode l1=ListNode.init(new int[]{2,6,8});
//        ListNode l2=ListNode.init(new int[]{1,5,10});
//        ListNode arr[] = new ListNode[2];
//        arr[0]=l1;
//        arr[1]=l2;
//        ListNode x=m.mergeKLists(arr);
//        ListNode.print(x);
//
//        ListNode l3=ListNode.init(new int[]{2});
//        ListNode l4=null;
//        ListNode l5=ListNode.init(new int[]{-1});
//
//        arr = new ListNode[3];
//        arr[0]=l3;
//        arr[1]=l4;
//        arr[2]=l5;
//         x=m.mergeKLists(arr);
//        ListNode.print(x);
//        ListNode[] arr1 =new ListNode[1];
//        arr1[0]=new ListNode(10);
//        System.out.println(arr1[0].val);
//        ListNode x=m.mergeKLists2(arr1);
//        ListNode.print(x);

//        ListNode head = ListNode.init(new int[]{1,2,3,4});
//        ListNode.print(head);
//
//        ListNode result=m.swapPairs(head);
//        ListNode.print(result);

        ListNode head = ListNode.init(new int[]{1});
        ListNode.print(head);

        ListNode result=m.swapPairs(head);
        ListNode.print(result);

    }

    //divide and conquer
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null || lists.length==0)
        return null;
        ListNode result=lists[0];
        System.out.println(lists);
        int i=0;
        for(int j=lists.length-1;i<j;i++,j--){
            lists[i] = mergeLists(lists[i],lists[j]);
        }
        return result;
    }
    private ListNode mergeLists(ListNode ptr1, ListNode ptr2) {
        ListNode prev=null;
        ListNode cur;

        if((ptr1!=null && ptr2!=null && ptr1.val<=ptr2.val) || (ptr1!=null && ptr2==null)) {
            prev = ptr1;
            ptr1=ptr1.next;
        }
        else if(ptr2!=null){
            prev = ptr2;
            ptr2=ptr2.next;
        }

        cur=prev;
        while(ptr1!=null && ptr2!=null) {
            if(ptr1.val<=ptr2.val){
                prev.next=ptr1;

                ptr1=ptr1.next;
            }
            else if( ptr2.val<ptr1.val){
                prev.next=ptr2;

                ptr2=ptr2.next;
            }
            System.out.println(prev.val);
            prev=prev.next;

        }
        if(ptr1!=null){
            prev.next=ptr1;

        }
        if(ptr2!=null) {
            prev.next = ptr2;
        }
        return cur;
    }

    //priority queue
    public ListNode mergeKLists2(ListNode[] lists)
    {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length,(a, b) -> Integer.compare(a.val, b.val));

        ListNode head = new ListNode(0), tail = head;
        for (ListNode node : lists) if (node != null) heap.offer(node);
        while (!heap.isEmpty()) {
            tail.next = heap.poll();
            tail = tail.next;
            if (tail.next != null) heap.offer(tail.next);
        }
        return head.next;
    }

        public ListNode swapPairs(ListNode head) {
            ListNode cur,prev,tmp;
            prev=new ListNode(0);
            prev.next=head;
            cur=prev;
            if(head!=null && head.next!=null)
                head=head.next;
            while(cur.next!=null && cur.next.next!=null) {
                ListNode first=cur.next;
                ListNode sec=cur.next.next;
                tmp = sec.next;
                sec.next=first;
                first.next=tmp;
                cur.next=sec;
                cur=first;
                System.out.println(cur.val);

            }
            return head;
        }


    public int findLongestChain(int[][] pairs) {
        int count=1;
        Arrays.sort(pairs, (a,b) -> a[1]-b[1]);
        int prev=pairs[0][1];

        for(int i=1;i<pairs.length;i++) {
            if(prev<pairs[i][0]) {
                count++;
                prev=pairs[i][1];
            }
        }
        return count;
    }
}
//1->2 3->4
//2->1 3->4

//4->3
