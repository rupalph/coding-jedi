import java.util.Arrays;

/**
 * Created by rupalph on 11/3/19.
 */
class RotateArray {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k%nums.length;
        //reverse
        reverse(nums,0,nums.length);
        System.out.println(Arrays.toString(nums));


        reverse(nums,0,k);
        System.out.println(Arrays.toString(nums));


        //reverse k to len-1
        reverse(nums,k,len);
        System.out.println(Arrays.toString(nums));


    }

    private void reverse(int[] nums, int k, int len){
        for(int i=k,j=len-1;i<j;i++,j--){
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }


    public static void main(String[] args){
        RotateArray rotateArray = new RotateArray();
        rotateArray.rotate(new int[]{1,2,3,4,5,6,7},3);
    }
}