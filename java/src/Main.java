import java.util.*;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        System.out.println("longest substring length "+lengthOfLongestSubstring("pwwkew"));
//        System.out.println("longest substring length "+lengthOfLongestSubstring("abcabcbb"));
//        System.out.println("longest substring length "+lengthOfLongestSubstring("bbbbbbbb"));

       // System.out.println("longest palindromic substring length "+lengthOfPalindromicSubstring("abcdeabaedg"));
//        int[][] pairs={{3,4},{2,3},{1,2}};
//        System.out.println("longest chain length "+findLongestChain(pairs));

//        System.out.println("group anagrams "+groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
//
//        System.out.println("is palindrome "+isPalindrome(121));
//        System.out.println("is palindrome "+isPalindrome(1221));

//        System.out.println("median "+findMedianSortedArrays(new int[]{1,2,3,4,5,6},new int[]{10}));

        int k=0;
        while(k<10) {
            System.out.println((k%3) + " ");
            if((k%3) == 0)
                k= k+2;
            else
                k++;

        }
        double a = 1.1;
        double b = 1.2;
        ArrayList<Integer> num = new ArrayList<>(Arrays.asList(1,5, 15, 50, 75, 100));
        for(int i=0;i<num.size();i++){
            System.out.println(i+":"+num.get(i));
            if(num.get(i).intValue() % 5 == 0)
                num.remove(i);
        }

        System.out.println(num);
    }

        public static int lengthOfLongestSubstring(String s) {

            int start = 0;
            int end = 0;
            int max = Integer.MIN_VALUE;

            HashMap<Character,Integer> uniquec = new HashMap<>();
            uniquec.put(s.charAt(0),0);
            for (int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);
                if (uniquec.containsKey(c)) {

                    char d = s.charAt(start);
                    start=i;
                    uniquec.remove(d);


                } else {
                    if ((i - start + 1) > max)
                        max = (i - start + 1);

                    uniquec.put(c,i);
                }
            }

            return max;
        }



    public static int lengthOfPalindromicSubstring(String s) {

        int start = 0;
        int end = 0;
        int max = 1;

        boolean dp[][] = new boolean[s.length()][s.length()];

        for(int i=0;i<s.length();i++)
            dp[i][i] = true;

        for(int i=0;i<s.length()-1;i++) {
            if (s.charAt(i) == s.charAt(i + 1) ) {
                dp[i][i + 1] = true;
                max = 2;
            }
        }


        int n=s.length();
        // Check for lengths greater than 2. k is length
        // of substring
        for (int k = 3; k <= n; ++k)
        {
            // Fix the starting index
            for (int i = 0; i < n-k+1 ; ++i)
            {
                // Get the ending index of substring from
                // starting index i and length k
                int j = i + k - 1;

                // checking for sub-string from ith index to
                // jth index iff str[i+1] to str[j-1] is a
                // palindrome
                if (dp[i+1][j-1] && s.charAt(i) == s.charAt(j))
                {
                    dp[i][j] = true;

                    if (k > max)
                    {
                        start = i;
                        max = k;
                    }
                }
            }
        }

        System.out.println(max);
        System.out.println("longest palindromic substring "+ s.substring(start,start+max));
        return max;
    }

        public static int findLongestChain(int[][] pairs) {
            int count=1;
            Arrays.sort(pairs, (a, b) -> a[1]-b[1]);
            int prev=pairs[0][1];

            for(int i=1;i<pairs.length;i++) {
                if(prev<pairs[i][0]) {
                    count++;
                    prev=pairs[i][1];
                }
            }
            return count;
        }


    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map=new HashMap<>();
        for(String str:strs){
            char[] arr=str.toCharArray();
            Arrays.sort(arr);
            String key=new String(arr);
            if(map.containsKey(key)){
                List<String> list=map.get(key);
                list.add(str);
            }else {
                List<String> list=new ArrayList<>();
                list.add(str);
                map.put(key,list);
            }

        }
        List<List<String>> result=new ArrayList<>(map.values());
        return result;
    }

    public static boolean isPalindrome(int x) {
        int rev=0,tmp=x;
        if(x<0)
            return false;
        else if(x<10)
            return true;
        while(x>0){
            int lsb=x%10;
            rev = rev*10+lsb;
            x=x/10;
            System.out.println(rev);

        }
        System.out.println(rev);
        return( (tmp==rev) || (rev==tmp/10));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {


        //1,5,8

        //2,6

        //k=1, i = 1, j=0
        //k=2, i = 1, j=1
        //k=3, i=2, j=1

        //1 2 5 6 8
        int i = 0,j=0;
        boolean findAvg=false;
        int mid = ((nums1.length + nums2.length) / 2 );
        if((nums1.length + nums2.length)%2==0) {
            mid--;
            findAvg = true;
        }
        double median=0.0,median1=0.0;
        for(int k=0;k<nums1.length+nums2.length; k++){

            if((i<nums1.length && j<nums2.length && nums1[i] < nums2[j]) || (i < nums1.length && j >= nums2.length))
            {
                if(mid==k && median==0.0)
                    median = nums1[i];
                else if(median!=0.0)
                    median1 = nums1[i];
                i++;
            }
            else if(j<nums2.length)
            {
                if(mid==k && median==0.0)
                    median = nums2[j];
                else if(median!=0.0)
                    median1 = nums2[j];

                j++;
            }
            if(median!=0.0 && median1!=0.0)
                break;
        }
        System.out.println(median + " "+median1);
        if(findAvg)
            return (median+median1)/2.0;
        return median;

    }
}

