
import com.sun.deploy.util.StringUtils;

import java.util.*;

/**
 * Created by rupalh on 9/15/17.
 */
public class ArrayProblems {

    /*
    1 2 0   0 0 0
    3 4 5   3 4 0
    8 9 1   8 9 0
     */
    public void setZeroes(int[][] matrix) {
        boolean row[]=new boolean[matrix.length];
        boolean col[]=new boolean[matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0) {
                    row[i] = true;
                    col[j] = true;
                }

            }
        }
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(row[i]) {
                    for (int k = 0; k < matrix[0].length; k++)
                        matrix[i][k] = 0;
                }
                if(col[j]) {
                    for (int k = 0; k < matrix.length; k++)
                        matrix[k][j] = 0;
                }
            }
        }
    }

        //0 1 2 0 2 1 2 0
        public void sortColors(int[] nums) {
            int low=0;
            int mid=0;
            int high=nums.length-1;
            while(mid<=high) {
                System.out.println(Arrays.toString(nums)+":"+nums[mid]);
                if(nums[mid]==0)
                {
                    int t=nums[low];
                    nums[low]=nums[mid];
                    nums[mid]=t;
                    low++;mid++;
                }
                else if(nums[mid]==1)
                    mid++;
                else if(nums[mid]==2){
                    int t=nums[mid];
                    nums[mid]=nums[high];
                    nums[high]=t;
                    high--;
                }
            }

        }

        public void simplifyUrl(String url){

            String[] parts = url.split("/");

            LinkedList<String> list = new LinkedList<String>();
            for(String part:parts){
                if(part.equals("..")){
                    if(!list.isEmpty())
                        list.removeLast();
                }
                else
                    list.add(part);

            }


            System.out.println(StringUtils.join(list,"/"));

        }

        public int findKthLargest(int[] nums, int lo, int hi,int k){
            int start=lo;
            int pivot = nums[lo];
            for(int i=lo+1;i<hi;i++){
                if(nums[i]>pivot){
                    start++;
                    swap(nums,i,start);
                    System.out.println(Arrays.toString(nums));

                }
            }
            swap(nums,lo,start);
            if(start+1==k)
                return pivot;
            else if(start+1>k)
                return findKthLargest(nums,lo,start,k);
            else
                return findKthLargest(nums,start+1,hi,k);

        }

        public void swap(int arr[], int i, int j){
            int tmp=arr[i];
            arr[i]=arr[j];
            arr[j]=tmp;
        }
    public int search(int[] nums, int target) {

        int lo=0;
        int hi=nums.length-1;
        int mid =0;
        while(lo<hi){
            mid = (lo+hi)/2;
            //System.out.println(mid);
            if(nums[mid]==target)
                return mid;
            else if(nums[lo]<=nums[mid]) {
                if (target<nums[mid]&&target>=nums[lo])
                    hi=mid-1;
                else
                    lo=mid+1;
            }
            else {
                if(target>nums[mid]&&target<=nums[hi])
                    lo=mid+1;
                else
                    hi=mid-1;
            }

        }
        //System.out.println(lo+":"+hi);
        return nums[lo]==target?lo:-1;
    }



    public int findIslands(int[][] grid){
        int count=0;
        for(int i=0;i<grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (check(grid, i, j, count))
                    count++;
            }
        }
        return count;
    }

    private boolean check(int[][] grid, int i, int j, int count) {
        if(i<0 || i>= grid.length || j<0 || j>=grid[0].length || grid[i][j]==0)
            return false;
        grid[i][j]=0;
        check(grid,i+1,j,count);
        check(grid,i-1,j,count);
        check(grid,i,j+1,count);
        check(grid,i,j-1,count);
        return true;
    }

    public boolean patternMatch(String str, String pattern) {
        Map<Character,String > map =new HashMap<>();
        boolean result= patternMatch(str,pattern,0,0,1,map);
        System.out.println("***+"+map);
        return result;
    }

    private boolean patternMatch(String str, String pattern, int i, int j, int len, Map<Character,String> map)  {
        System.out.println(map);
//        if(i>str.length() || j> pattern.length())
//            return false;
        if(i==str.length() && j ==pattern.length())
            return true;
        if(i==str.length() || j ==pattern.length())
            return false;
        char ch = pattern.charAt(j);
        if(map.containsKey(ch)) {
            String val = map.get(ch);
            int l = val.length();
//            System.out.println("ch="+ch+" for i ="+i+",j="+j+",len="+l);
            if(i+l<=str.length()) {
                String subStr = str.substring(i, i + l);
                if (!val.equals(subStr))
                    return false;
                if(patternMatch(str, pattern, i + val.length(), j + 1, len, map))
                    return true;
            }
            else
                return false;
        }
            for(int k=1;i+k<=str.length();k++) {
                if(map.containsValue(str.substring(i,i+k)))
                    continue;

                map.put(ch,str.substring(i,i+k));
//                System.out.println("for i ="+i+",j="+j+",k="+k);

                if(patternMatch(str, pattern, i + k, j+1, k, map))
                               return true;
                map.remove(ch);
            }


        return false;

    }
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
        System.out.println(map);
        // base case
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;

        // get current pattern character
        char c = pat.charAt(j);

        // if the pattern character exists
        if (map.containsKey(c)) {
            String s = map.get(c);

            // then check if we can use it to match str[i...i+s.length()]
            if (!str.startsWith(s, i)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }

        // pattern character does not exist in the map
        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1);

            if (set.contains(p)) {
                continue;
            }

            // create or update it
            map.put(c, p);
            set.add(p);

            // continue to match the rest
            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }

            // backtracking
            map.remove(c);
            set.remove(p);
        }

        // we've tried our best but still no luck
        return false;
    }

    public int findMin(int[] nums) {
        int lo = 0, high = nums.length-1;
        int min = Integer.MAX_VALUE;
        while(lo<high){
            int mid = (lo+high)/2;
            System.out.println(min+" "+lo+" "+high+" "+mid);
            if(nums[lo]==nums[high] && lo!=high)
                lo++;
            else if(nums[lo]<=nums[mid]){
                min = Math.min(min, nums[lo]);
                lo = mid+1;
            }else {
                min = Math.min(min, nums[mid]);
                high = mid-1;
            }
        }
        return Math.min(min,nums[lo]);
    }

    //recursive
    public boolean minJump(int[] arr, int i, int jump){
        if(i>=arr.length)
            return true;
        if(arr[i]==0)
            return false;
        for(int j=arr[i]; j>-0;j--) {
            if(minJump(arr,i+j,jump+1))
                return true;
        }
        return false;
    }

    //iterative
    public boolean minJump2(int[] A) {
        if(A.length <= 1)
            return true;

        int max = A[0]; //max stands for the largest index that can be reached.

        for(int i=0; i<A.length; i++){
            //if not enough to go to next
            if(max <= i && A[i] == 0)
                return false;

            //update max
            if(i + A[i] > max){
                max = i + A[i];
            }

            //max is enough to reach the end
            if(max >= A.length-1)
                return true;
        }

        return false;
    }

    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length==1)
            return 0;

        int step = 1; //no of jumps
        int max = nums[0]; //max position in array you can reach at the moment
        int prevMax = 0;
        for(int i=1;i<nums.length; i++){
            if(max>=nums.length-1)
                return step;
            if(i>prevMax) {
                step++;
                prevMax = max;
            }
            if((i+nums[i])>max) {
                max = i+nums[i];

            }
        }
        if(max<nums.length-1)
            return 0;
        return step;
    }

    public static void main(String args[]){
        ArrayProblems ap=new ArrayProblems();
        //ap.sortColors(new int[]{0,1,2,0,1,0});

//        String url = "a/../b/c";
//        ap.simplifyUrl(url);

        int[] input = new int[]{6,7,0,1,2,3,4,5};
//        System.out.println(Arrays.toString(input));
//
//        System.out.println(ap.findKthLargest(input,0,input.length,2));
//
//        System.out.println(ap.search(input,7));
//        System.out.println(ap.search(input,0));
//        System.out.println(ap.search(input,4));

//        int[][] input2 = new int[][]{{6,7,0},{1,2,3}};
//        ap.setZeroes(input2);
//
//        for (int[] row : input2){
//            System.out.println(Arrays.toString(row));
//        }

//        int[][] grid = new int[][]{{1, 1, 0,0,0},{1,0,0,0,1}, {0,0,0,1,1}, {0,0,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
//
//        System.out.println(ap.findIslands(grid));

//          System.out.println(ap.patternMatch("redblue","ab"));
//        System.out.println(ap.patternMatch("redbluered","aba"));
//
//        System.out.println(ap.patternMatch("redblueredblue","abab"));
//        System.out.println(ap.wordPatternMatch("abab","redblueredblue"));

//        System.out.println(ap.findMin(new int[]{3,3,1,3,3,3,3}));

//        System.out.println(ap.minJump(new int[]{2,3,1,1,2,4,2,0,1,1},0,0));
//        System.out.println(ap.minJump(new int[]{4,2,1,0,2,1},0,0));
//        System.out.println(ap.minJump(new int[]{4,2,1,0,0,1},0,0));
        System.out.println(ap.minJump2(new int[]{4,2,1,0,0,1}));
        System.out.println(ap.minJump2(new int[]{2,3,1,1,2,4,2,0,1,1}));

        System.out.println(ap.jump(new int[]{3,4,0,0,1,1,1,1,1}));

        System.out.println(ap.jump(new int[]{4,2,1,0,2,1}));

    }
}

