/**
 * Created by rupalh on 1/7/18.
 */
public class MyList {
    public static void deleteDups2(LinkedListNode head) {
       if (head == null) return;
         LinkedListNode previous = head;
         LinkedListNode current = previous.next;
         while (current != null) {
             LinkedListNode runner = head;
             System.out.println("outside current:"+current.data);

             while (runner != current) { // Check for earlier dups
                 System.out.println("runner:"+runner.data);

                 if (runner.data == current.data) {
                     System.out.println("dup");
                     LinkedListNode tmp = current.next; // remove current
                     previous.next = tmp;
                     current = tmp; // update current to next node
                     break; // all other dups have already been removed
                     }
                 runner = runner.next;
             }
             System.out.println("runner:"+runner.data);

             if (runner == current) { // current not updated ‐ update now
                 previous = current;
                 current = current.next;
                 }
             }
             LinkedListNode.printMe(head);
         }

         public static LinkedListNode reverse(LinkedListNode node){
             LinkedListNode cur = node;
             LinkedListNode prev = null;

             while(cur!=null){
                 LinkedListNode tmp=cur.next;
//                 cur.next.next = cur;
                 cur.next=prev;
                 prev = cur;
                 cur = tmp;
             }

             return prev;
         }

    public static LinkedListNode reorder(LinkedListNode node){
        LinkedListNode slow = node;
        LinkedListNode fast = node;

        //find mid
        while(slow!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        LinkedListNode midPre = slow;
        LinkedListNode mid = midPre.next;

        LinkedListNode newHead = reverse(mid);
        midPre.next = newHead;

        LinkedListNode.printMe(node);
        System.out.println("midPre:"+midPre.data);
//        System.out.println("mid:"+mid.data);

        //merge first part with 2nd part
        LinkedListNode cur = node;
        LinkedListNode head2 = cur;
        mid = midPre.next;
        while(cur!=null && mid!=null){
            System.out.println(cur.data + " "+mid.data);
            midPre.next = mid.next;

            mid.next = cur.next;
            cur.next = mid;
            cur=mid.next;
            mid = midPre.next;
//            System.out.println(cur.data + " "+mid.data);

//            break;
        }
//        midPre.next=null;
        return head2;
    }

    public static void main(String[] args) {
//        LinkedListNode head = LinkedListNode.createList(new int[]{14,5,6,3,14,3,5});
//        MyList.deleteDups2(head);
        LinkedListNode head2 = LinkedListNode.createList(new int[]{1,2,3,4,5,6});

        LinkedListNode newHead = MyList.reorder(head2);
        LinkedListNode.printMe(newHead);
    }
}

class LinkedListNode {
    int data;
    LinkedListNode next;

    LinkedListNode(int d){
        data = d;
        next=null;
    }
    public static LinkedListNode createList(int[] arr){
        LinkedListNode head = new LinkedListNode(arr[0]);
        LinkedListNode cur = head;
        for(int i=1;i<arr.length;i++) {
            cur.next = new LinkedListNode(arr[i]);
            cur = cur.next;
        }

        return head;
    }

    public static void printMe(LinkedListNode head){

        System.out.println();
        LinkedListNode cur = head;
        while(cur!=null){
            System.out.print(cur.data+"->");
            cur = cur.next;
        }

        System.out.println();

    }
}
