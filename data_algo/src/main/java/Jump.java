import org.junit.Test;

/**
 * Created by rupalph on 8/10/19.
 */
public class Jump {

    enum type { GOOD, BAD};

    public boolean canJump(int[] arr){
        //
        // arr from each index try to jump max
        // if  jump from cur to max then back track if can't jump out

        // [ 2 2 0 1 3 ]
        // 2-> max jump 0, back track
        // 2-> max jump 2, 2 to max jump 1, 1 to 3 .. looks good, return true

        // chek if you can reach last position from 2nd last, go from right to left
        if(arr==null || arr.length==0)
            return false;
        boolean[] memo = new boolean[arr.length];
        memo[arr.length-1] = true;
        for(int i=arr.length-2;i>=0;i--){
            int reach = Math.min(i+arr[i], arr.length - 1);
            for(int j=i+1;j<=reach;j++){
                if(memo[j]) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[0];

    }

    public boolean canJump2(int[] arr){
        //
        // arr from each index try to jump max
        // if  jump from cur to max then back track if can't jump out

        // [ 2 2 0 1 3 ]
        // 2-> max jump 0, back track
        // 2-> max jump 2, 2 to max jump 1, 1 to 3 .. looks good, return true

        // chek if you can reach last position from 2nd last, go from right to left
        if(arr==null || arr.length==0)
            return false;
        boolean[] memo = new boolean[arr.length];
        memo[arr.length-1] = true;
        int prevReach = arr.length - 1;
        for(int i=arr.length-2;i>=0;i--){
            int reach = Math.min(i+arr[i], arr.length - 1);
            //System.out.println("memo[reach]"+memo[reach]+", reach:"+reach );
            if(arr[i]>0 && (memo[reach] || memo[prevReach])) {
                memo[i] = true;
                prevReach = reach;
            }
        }
//        for(int i=0;i<arr.length;i++)
//            System.out.print(memo[i]+" ");
//        System.out.println();
        return memo[0];

    }

    public int minJumps(int[] arr){
        //
        // arr from each index try to jump max
        // if  jump from cur to max then back track if can't jump out

        // [ 2 2 0 1 3 ]
        // 2-> max jump 0, back track
        // 2-> max jump 2, 2 to max jump 1, 1 to 3 .. looks good, return true

        // chek if you can reach last position from 2nd last, go from right to left
        if(arr==null || arr.length==0)
            return 0;
        boolean[] memo = new boolean[arr.length];
        memo[arr.length-1] = true;
        int prevReach = arr.length - 1;
        int steps=0;
        for(int i=arr.length-2;i>=0;i--){
            int reach = Math.min(i+arr[i], arr.length - 1);
            //System.out.println("memo[reach]"+memo[reach]+", reach:"+reach );
            if(i+arr[i]>= arr[prevReach]) {
                memo[i] = true;
                prevReach = reach;
                steps++;
            }
        }
        for(int i=0;i<arr.length;i++)
            System.out.print(memo[i]+" ");
        System.out.println();
        return steps;

    }

    @Test
    public void test1(){
        Jump j = new Jump();
        boolean res = j.canJump(new int[]{2,3,1,1,4});
        System.out.println(res);

    }

    @Test
    public void test2(){
        Jump j = new Jump();
        boolean res = j.canJump(new int[]{3,2,1,0,4});
        System.out.println(res);

    }

    @Test
    public void test3(){
        Jump j = new Jump();
        boolean res = j.canJump(new int[]{2, 2, 0, 1, 3});
        System.out.println(res);

    }

    @Test
    public void test4(){
        Jump j = new Jump();
        boolean res = j.canJump2(new int[]{2,3,1,1,4});
        System.out.println(res);

    }

    @Test
    public void test5(){
        Jump j = new Jump();
        boolean res = j.canJump2(new int[]{3,2,1,0,4});
        System.out.println(res);

    }

    @Test
    public void test6(){
        Jump j = new Jump();
        boolean res = j.canJump2(new int[]{2, 2, 0, 1, 3});
        System.out.println(res);

    }

    @Test
    public void test7(){
        Jump j = new Jump();
        int res = j.minJumps(new int[]{2, 2, 0, 1, 3});
        System.out.println(res);

    }

}
