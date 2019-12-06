import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rupalph on 8/25/19.
 */
public class Palindrom {

    /*
     * Complete the function below.
     */
    static String[] generate_palindromic_decompositions(String s) {
        return null;
    }
    //abcdefg
    //abc
    //bcd
    //cde
    //efg

    static void helper(String s, int pos, LinkedList<String> palindromes,         List<List<String>> result) {
//        System.out.println();
        if(pos==s.length()) {
            result.add(new ArrayList(palindromes));
            return;
        }
        for(int i=pos+1;i<=s.length();i++) {
            String word = s.substring(pos,i);
            if(isPalindrome(word)) {
                palindromes.add(word);
                helper(s, i , palindromes, result);
                palindromes.removeLast();
            }
            //helper(s, i-pos+1, prefix + "|" + word, palindromes);
        }

    }

    static void allSubstrings(String s, int pos, String prefix) {
//        System.out.println();
        if(pos==s.length()) {
            System.out.println(prefix);
            return;
        }

        allSubstrings(s, pos+1, prefix + s.charAt(pos));
        allSubstrings(s, pos+1, prefix );

    }

    private static boolean isPalindrome(String s) {
        if(s.length()==1)
            return true;
        int j = s.length()-1;
        for(int i=0;i<s.length();i++)
            if(s.charAt(i)!=s.charAt(j--))
                return false;
        return true;
    }

    public static void main(String[] args){
        LinkedList<String> output = new LinkedList<>();
        List<List<String>> output2 = new ArrayList<>();

        helper("abracadabra", 0,  output, output2);
        System.out.println(output);
        System.out.println(output2);

//        allSubstrings("abracadabra", 0, "");

    }


    /*
    // one solution is to find all substrings recursively and check if palindrome

    1. check if string is palindrome
    2. check if 3 length substrings r palindromes
    3. check if 4 length substrings r palindromes
    4. check if 5 length substrings r palindromes..
    5  check if n-1 length substrings r palindromes
    6.
     */

}
