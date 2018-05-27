import java.util.Arrays;

/**
 * Created by rupalh on 5/12/18.
 */
public class SortColors {

        public int[] callMe(int[] nums) {
            int i = 0;
            int j = nums.length - 1;

            while (i < nums.length && nums[i] == 0)
                i++;
            while (j >= 0 && nums[j] == 2)
                j--;

            if (i >= nums.length || j < 0) return nums;

            int start = i, end = j;
            int cur = i;
            while(cur<=end){

                if(nums[cur]==0){
                    swap(nums,cur,start);
                    start++; cur++;
                }
                else if(nums[cur]==2){
                    swap(nums,cur,end);
                    end--;
                }
                else
                    cur++;
            }

            return nums;
        }

        void swap(int[] nums, int i, int j){
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }


    public static void main(String[] args){
        SortColors sc=new SortColors();
        int[] res = sc.callMe(new int[]{0,2,2,2,0,2,1,1});
        System.out.println(Arrays.toString(res));
    }
}
