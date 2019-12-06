package com.code.samples;

import java.util.Arrays;

import org.junit.Test;

public class ArrayProblems {
    public static void main(String[] args) {
	        int[] intArr={3, -1, -1, -1, -1, -1, 2,-5, 10, 0 };
	        //int[] intArr = {-1, 3, -5, 4, 6, -1, 2, -7, 13, -3};
	        //int[] intArr={-6,-2,-3,-4,-1,-5,-5};
	        findMaxSubArray(intArr);
	    }
	public static void findMaxSubArray(int[] inputArray){
		int maxSum=inputArray[0];
		int start=0,starti=0;
		int end=0;int sum=inputArray[0],min=0;
		for(int i=1;i<inputArray.length;i++)
		{
			 sum +=inputArray[i]; //-8 -3 -7
			
			if(sum>maxSum) {
				end=i;
				maxSum=sum;
				start=starti;
				//result[i]=maxSum;
			}
			else if(sum<0) {
				min=sum; //-8
				sum = 0; //
				starti=i+1;
				//result[i]=min;
			}
		}
		
		System.out.println(maxSum+" "+start+" "+end);
		
	}
	
	//{ 1,1,2,2,3,3,3,4,4,5,6,6}
	/**
	 * Find odd occurrence of a number in a array
	 * @param arr
	 */
	public void findOdds(int[] arr)
	{
		Arrays.sort(arr);
		
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
		
		System.out.println();
		boolean even=false;
		int len=1;
		
		for(int i=1;i<arr.length;i++)
		{
			if(arr[i]==arr[i-1])
			{
				even=!even;
				len++;
			}
			else
			{
				if(len%2!=0 && !even)
					System.out.print(arr[i-1]+" ");
				even=false;
				len=1;
			}
			
		}
		
	}
	
	
	@Test
	public void testFindOdds()
	{
		int [] a={3,4,5,3,4,6,1,1,2,2,6,3};
		
		findOdds(a);
	}
	
	public void findPairsofSumx(int a[],int x)
	{
		//{1,3,5,8,0,4,2,6}
		//x=5 = 1,4 3,2 5,0 
		
		
		//1,4
	}
	
	//find intersection
	public int[] findIntersection (int [] a,int[] b)
	{
		//modify array a with common elements
		//take an element in 1st array, scan it in 2nd array if find it output it 
		//otherwise do nothing but this is n^2 solution
		
		// o(n) solution - scan 1st array - build hashset 
		// scan 2nd array check if present in hashset, if yes then output
		// this requires additional memory
		
		return a;
	}
}