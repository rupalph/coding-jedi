import java.util.Arrays;
import java.util.LinkedList;

public class Tower {
    public static void main(String[] args){
        int[] arr = new int[] { 1, 2, 3, 4};
        Tower hanoi = new Tower(arr);
        Tower first = new Tower();
        Tower second = new Tower();
        hanoi.moveDisks(3,first, second);

    }

    LinkedList<Integer> disks=new LinkedList<>();
    Tower(int[] arr) {
        if(arr.length>0)
            Arrays.stream(arr).forEach( x -> disks.add(x));
    }

    Tower() {
        //do nothing
    }
    void move(Tower other){
        if(!disks.isEmpty()) {
            int top = disks.pop();
            other.push(top);
        }
    }

    private void push(int top) {
    }
    private boolean isEmpty() {
        return  false;
    }

    void add(int x){
        disks.push(x);
    }

    void print() {
        System.out.println(disks);
    }

    public void moveDisks(int n, Tower first, Tower second){
        if(n<0)
            return;
        while(!first.isEmpty()){
            first.move(second);
        }

        int cur=disks.get(n);
        first.add(cur);

        while(!second.isEmpty()){
            second.move(first);
        }

        moveDisks(n-1,first,second);
    }
}
