package leetcode;

/**
 * Created by rupalph on 12/1/19.
 */
public class PalindromePartitioning {

    private static int globalMin = Integer.MAX_VALUE;
    public static int partition(String s, int k){
        //partition string s in k substrings
        //each of k substring is palindrome
        //find minimum such partitioning with minimum edits

        //for e.g abc ==> aac with split = 1 aa|c

        partitionRecursive(s,k, "",0, 0, s.length());

        return globalMin;
    }

    private static int splitRecursive(String s, int k, String prefix, int start, int end){

        if(k==1) {
            System.out.println(prefix+ ","+s.substring(start));
            return 0;
        }
            for(int i=start+1;i<=end;i++) {
                splitRecursive(s,k-1,prefix+","+s.substring(start,i),i,end);
            }

        return 0;
    }

    private static void partitionRecursive(String s, int k, String prefix, int minEdits, int start, int end){

        if(k==1) {
            int min = minEdits+palindromEdits(s.substring(start));
            System.out.println(prefix+","+s.substring(start)+" min:"+min);
            globalMin = Math.min(globalMin,min);
            //return min;
        }
        for(int i=start+1;i<=end;i++) {
            partitionRecursive(s,k-1,prefix+","+s.substring(start,i),minEdits+palindromEdits(s.substring(start,i)),i,end);
        }

        //return 0;
    }

    private static void partitionRecursive2(String s, int k, int minEdits, int start){

        if(k==1) {
            int min = minEdits+palindromEdits(s.substring(start));
//            System.out.println(prefix+","+s.substring(start)+" min:"+min);
            globalMin = Math.min(globalMin,min);
            //return min;
        }
        for(int i=start+1;i<=s.length();i++) {
            partitionRecursive2(s,k-1,minEdits+palindromEdits(s.substring(start,i)),i);
        }

        //return 0;
    }

    private static void partitionDP(String s, int k){

       // if(k==1) {
            //int min = minEdits+palindromEdits(s.substring(start));
//            System.out.println(prefix+","+s.substring(start)+" min:"+min);
            //globalMin = Math.min(globalMin,min);
            //return min;
       // }
        int[][] dp= new int[s.length()+1][k+1];
        for(int len=k;len>0;len--) {
            int minEdits = 0;
                for (int i = 1; i <= s.length(); i++) {
                    for (int j = 0; j <= s.length(); j+=i) {

                        minEdits += palindromEdits(s.substring(j, i));
                    System.out.println(s.substring(j,i));
                }
            }
            System.out.println(minEdits);
        }

        //return 0;
    }

    private static void splitString(String s, int k, String prefix, int start, int end){

        for(int len=1;len<=s.length()-1;len++) {
        int i=0;
        int count=k;
        for( i=0,count=k;count>1&& i+len<s.length();i+=len,count-- ) {
                String split = s.substring(i, i+len);
                System.out.print(" "+split+",");
            }
            String split = s.substring(i);
            System.out.print(" "+split+",");

            System.out.println();
        }

    }

    private static int partitionWithMinEdits(String s, int k){
        int min = Integer.MAX_VALUE;
        for(int len=1;len<=s.length()-1;len++) {
            int i=0;
            int count=k;
            int minEdits = 0;
            for( i=0,count=k;count>1&& i+len<s.length();i+=len,count-- ) {
                String split = s.substring(i, i+len);
                System.out.print(" "+split+",");
                minEdits += palindromEdits(split);
            }
            String split = s.substring(i);
            System.out.print(" "+split+",");
            minEdits += palindromEdits(split);
            min = Math.min(min, minEdits);

            System.out.println("min edits: "+minEdits);
        }

        return min;
    }

    public static int palindromEdits(String s){
        int start=0;
        int end = s.length()-1;
        int count=0;
        while(start<=end){
            if(s.charAt(start)!=s.charAt(end))
                count++;
            start++;
            end--;
        }
        return count;

    }

    public static void main(String[] args){
        //partitionDP("abcd",2);
        partition("abcd",2);

//        int min = partition("spsvmwkvwyfnrrfklevvyxsayc" , 6);
//        System.out.println(min);
    }
}
