package com.code.samples;

//you can also use imports, for example:
import java.util.*;

import org.junit.Test;

//you can use System.out.println for debugging purposes, e.g.
//System.out.println("this is a debug message");

public class PostFixExpressionEvaluator {
 public int solution(String S) {
     // write your code in Java SE 8
     
     Stack st=new Stack();
     
     for(char c:S.toCharArray())
     {
         if(!Character.isDigit(c))
         {
        	 Integer n1=null,n2=null;
        	 
             if(!st.isEmpty()) n1=(Integer) st.pop();
             if(!st.isEmpty())  n2=(Integer) st.pop();
             if(n1!=null&& n2!=null) {
             if(c=='+')
                 st.push(n1+n2);
             else if(c=='*')
                 st.push(n1*n2);
             }
         }
         else if(Character.isDigit(c))
        	 st.push(Character.getNumericValue(c));	
             
     }
     if(!st.isEmpty())
     return (int) st.peek();
     else return 0;
 }
 
 @Test 
 public void testSolution()
 {
	 PostFixExpressionEvaluator t=new PostFixExpressionEvaluator();
	 //int ans=t.solution("13+62*7+*");
	 int ans=t.solution("11++");
	 System.out.println(ans);
 }
}

