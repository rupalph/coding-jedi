import java.util.Arrays;

/**
 * Created by rupalph on 8/25/19.
 */
public class SubArraySum {
    /*
     * Complete the function below.
     */
    static boolean check_if_sum_possible(long[] arr, long k) {
        return helper(arr, 0, k, false);
    }

    static boolean check_if_sum_possible2(long[] arr, long k) {
        return helper2(arr, 0, k, 0);
    }

    static boolean helper(long[] arr, int cur, long k, boolean included){

        if(k==0 && included)
            return true;
        else if(cur==arr.length)
            return false;

        boolean res1= helper(arr, cur+1, k-arr[cur], true ); //include
        if(res1==false)
            return helper(arr, cur+1, k, included ); //exclude

        return res1;
    }


    static boolean helper2(long[] arr, int cur, long k, int count){

        if(k==0 && count>1)
            return true;
        else if(cur==arr.length)
            return false;

        boolean res1= helper2(arr, cur+1, k-arr[cur], count+1 ); //include
        if(res1==false)
            return helper2(arr, cur+1, k, count ); //exclude

        return res1;
    }



    public static void main(String[] args){
//        long[] in1 = new long[] { 1,2,3,4,5,6,8};
//        boolean res1= check_if_sum_possible(in1, 10);
//        System.out.println(res1);
//
//
//        long[] in2 = new long[] { 1,2,2,5,6,8};
//        boolean res2= check_if_sum_possible2(in2, 4);
//        System.out.println(res2);


    }
}
