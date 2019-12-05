/**
 * Created by rupalph on 10/24/19.
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if(s.length()==0) return "";
        if(s.length()==1) return s;

        int maxLen = 0;
        String longestPalindrome = lp(s,0,1);
        for(int i=1;i<s.length()-1;i++) {
            String palindrome = lp(s,i-1,i+1);
            //even length palindrome e.g cbbc
            String palindrome2 = lp(s,i,i+1);
            if(palindrome2.length()>palindrome.length())
                palindrome = palindrome2;
            if(palindrome.length()>longestPalindrome.length())
                longestPalindrome = palindrome;

        }
        return longestPalindrome;

    }

    private String lp(String s, int start, int end){

        String palindrome = s.substring(start,start+1);
        boolean found = false;
        for(;start>=0&&end<s.length();start--,end++){
            if(s.charAt(start) != s.charAt(end)) {
                break;
            }
            else
                found = true;
        }
        if(found)
            palindrome = s.substring(start+1,end);
        return palindrome;
    }
}
