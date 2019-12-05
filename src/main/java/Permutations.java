import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rupalph on 11/3/19.
 */
class Permutations {
    public void printAllPermutations(int[] nums) {
        allPermutaions(nums, 0, new ArrayList<>(),new ArrayList<>());
    }

    private void allPermutaions(int[] nums, int pos, ArrayList<Integer> list, ArrayList<Integer> last) {
        if(pos == nums.length){
            System.out.println(list);
            last.clear();
            return;
        }
        for(int i=pos;i<nums.length;i++) {
            if(!last.isEmpty()) {
                list.add(last.remove(0));
            }
            else
                list.add(nums[pos]);
            //System.out.println("i="+i+"pos="+pos+"last="+last+"list="+list+"last="+last);
            allPermutaions(nums,pos+1, list,last);
            int num = list.remove(list.size()-1);
            last.add(num);
            //System.out.println("i="+i+"pos="+pos+"last="+last+"list="+list+"last="+last);
        }
    }


    private void nextPermutation(int[] nums, int start, int end) {
        int i= end;
        int pivot = 0;
       for(;i>start;i--){
           if (nums[i-1] < nums[i]) {
               pivot=i-1;
               break;
           }
       }
        int j=end;
       for(;j>pivot;j--){
           if(nums[j]>nums[pivot])
               break;
       }

       //swap
       int tmp = nums[pivot];
        nums[pivot] = nums[j];
        nums[j] = tmp;

        //reverse
        int k;
        for(k=pivot+1,j=nums.length-1;k<nums.length;k++,j--){
            tmp = nums[k];
            nums[k] = nums[j];
            nums[j] = tmp;
        }
    }

    private void rotate(int[] nums,int start, int end) {
        int tmp = nums[end];
        for(int i=end;i-1>=start;i--){
            nums[i] = nums[i-1];
        }
        nums[start] = tmp;
    }


    public static void main(String[] args){
        Permutations permutations = new Permutations();
        permutations.printAllPermutations(new int[]{1,2,3});
        System.out.println("All permutations ... ");
        int[] nums = new int[]{1,2,3};
        permutations.nextPermutation(nums);
        permutations.nextPermutation(new int[]{3,2,1});
        permutations.nextPermutation(new int[]{1,1,5});
        permutations.nextPermutation(new int[]{2,3,1});

    }

    private void nextPermutation(int[] nums) {
        nextPermutation(nums,0,2);
        System.out.println(Arrays.toString(nums));
    }
}