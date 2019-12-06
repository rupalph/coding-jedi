package algo;

public class LongestPalindromicSubString {

	
	private String findLongestPalindromicSubString(String str){

	    if(str == null || str.length() == 0)
	        return null;
	    else if(str.length() == 1)
	        return str;
	    else{
	        //Traverse down the string and which is each character
	        //check this logic.
	        int index=1;
	        String longestPalindrome=null;
	        while(index < str.length()/*||
	                (longestPalindrome != null && str.length() - index <= longestPalindrome.length())*/){
	            String currentPalindrome=null;
	            //Is it same as the previous character
	            //or is it same as the second last character.
	            //If so set a flag or call another method to check
	            //to find the longest palindrome string.
	            if(str.charAt(index) == str.charAt(index - 1))
	                currentPalindrome=readPalindrome(index - 1, index, str);
	            else if(index > 2 && str.charAt(index) == str.charAt(index - 2))
	                currentPalindrome=readPalindrome(index - 2, index, str);

	            if(currentPalindrome != null){
	                if(longestPalindrome == null || currentPalindrome.length() > longestPalindrome.length())
	                    longestPalindrome=currentPalindrome;
	                index+=currentPalindrome.length() / 2;
	            }else
	                index++;
	        }
	        return longestPalindrome;
	    }
	}

	private String readPalindrome(int i, int index, String str) {
		// TODO Auto-generated method stub
		return null;
	}
}
