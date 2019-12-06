package com.code.samples;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class StringProblems{

	
	public boolean isPalindrome(String s) {
		  int l = 0, r = s.length() - 1;
		  while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
		  return true;
		}
	
	public String reverse(String s) {
		  int l = 0, r = s.length() - 1;
		  char arr[]=s.toCharArray();
		  for(l=0;l<r;l++,r--)
		  {
			  char t=s.charAt(l); 
			  arr[l] = s.charAt(r) ;
			  arr[r]=t;
		  }
		  return String.valueOf(arr);
		}
	

	
	public List<String> findIntersection(List<List<String>> input)
	{
		Set<String> result=new HashSet<String>();
		
		int count=input.size();
		for(List<String> list:input)
		{
			if(count>1)result.addAll(list);
			else
				result.retainAll(list);
			count--;
			System.out.println(result);
		}
		
		List<String> res2=new ArrayList<String>();
		res2.addAll(result);
		
		return res2;
		
	}


	
	@Test
	public void testIsPalindrome()
	{
		StringProblems t=new StringProblems();
		assertTrue(t.isPalindrome("madam"));
		assertTrue(t.isPalindrome("stoppots"));

		assertFalse(t.isPalindrome("Is Madam"));
	}
	
	@Test
	public void testFindIntersection()
	{
		List<String> n1=Arrays.asList("A","B","C","D","E");
		List<String> n2=Arrays.asList("F","G","C","H","I");
		List<String> n3=Arrays.asList("A","B","K","K","C");
		
		StringProblems t=new StringProblems();

		List<List<String>> input=new ArrayList<List<String>>();
		input.add(n1);
		input.add(n2);
		input.add(n3);
		List res=t.findIntersection(input);
		
		System.out.println(res);
	}
}