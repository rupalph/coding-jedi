/**
 * Created by rupalph on 10/4/19.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*

Write a function to balance the parenthesis of a string.

The function will take as input a string of parenthesis (open and closed).
It will balance the parenthesis by removing the fewest possible characters
from the string, then return that shorter, balanced string.

A few examples:

balance( "()"  )  -> "()"
balance( ")("  )  -> ""

balance( "())"  ) -> "()"
balance( ")()"  ) -> "()"
balance( "()("  ) -> "()"
balance( "(()"  ) -> "()"
balance( ")(())" ) -> "(())"   !== "()()"

(())(()

( ( ) ) ( ( )


layer 0, (), balanced

layer 1, (), balanced

layer 0, (
layer 1, () balanced
((())()
(()(())


( (()()) (() ) layer 0, ( ; layer 1 , 2 ; layer 2, 3

(()()) () )

layer 0,
layer 1, ()

(()
*/

public class RemoveInvalidParenthesis {

    public static void removeInvalidParenthesis(String input){
        int left = 0;
        int right = 0;
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<input.length();i++) {
            if(input.charAt(i)=='(') left++;
            else if(input.charAt(i)==')') right++;
            if(left>=right)
                sb.append(input.charAt(i));
            else
                right--;
        }
        System.out.println(sb.toString());

        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<input.length();i++) {
            if(input.charAt(i)=='(')
                stack.push(i);
            else if(input.charAt(i)==')' && !stack.isEmpty())
                stack.pop();
        }
        while(!stack.isEmpty()) {
            int pos = stack.pop();
            sb.delete(pos,pos+1);
        }
        System.out.println(sb.toString());
    }


    public static String removeInvalidParenthesis2(String input) {
        int count = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            System.out.print(count+ " ");
            if (input.charAt(i) == '(') count++;
            else if (input.charAt(i) == ')') {
                count--;
                if (count < 0) { count=0; continue;}
            }
            sb.append(input.charAt(i));

        }
        System.out.println(count);
        count=0;
        for (int i = sb.length()-1; i >=0; i--) {
            System.out.print(count+ " ");

            if (sb.charAt(i) == ')') count++;
            else if (sb.charAt(i) == '(')  {
                count--;
                if (count < 0) { count = 0; sb.deleteCharAt(i); }
            }
        }
        System.out.println(count);

        return sb.toString();
    }
    public static void main(String args[] ) throws Exception {
        System.out.println("Hello World");

//        removeInvalidParenthesis("((())(())(");
//
//        removeInvalidParenthesis("()())()");

        String s1= removeInvalidParenthesis2("((())(())(");

        String s2 = removeInvalidParenthesis2("()())()");

        System.out.println(s1+" , "+ s2);
    }
}