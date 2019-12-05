package com.code.samples;

import java.util.Arrays;

import org.junit.Test;

public class MergeSort {

	public int[] partition(int[] arr, int start, int end)
	{
		int res[]=null;
		System.out.println("start "+start+" end "+end);

		if(start==end) {
			res=new int[1];
			res[0]=arr[start];
			
		}
		else if(start<end){
		
			int mid=(start+end)/2;
			int[] arrA = partition(arr,start,mid);
			int[] arrB = partition(arr,mid+1,end);
			
			res=merge(arrA,arrB);
			
			
		}
		return res;
		
	}

	private int[] merge(int[] arrA, int[] arrB) {
		int i=0;
		int j=0;
		int k=0;
		int[] res=new int[arrA.length+arrB.length];
		while(i<arrA.length && j<arrB.length)
		{
			if(arrA[i] < arrB[j])
			{
				res[k++]=arrA[i++];
			}
			else
				res[k++]=arrB[j++];
			
		}
		while(i<arrA.length)
			res[k++]=arrA[i++];
		while(j<arrB.length)
			res[k++]=arrB[j++];
		
		return res;
	}
	
	public void print(int[] a)
	{
		for(int i=0;i<a.length;i++)
		{
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	@Test
	public void testMergeSort()
	{
		int a[]={10,2,8,4,5,21,3};
		
		MergeSort m=new MergeSort();
		int[] res=m.partition(a, 0, a.length-1);
		
		m.print(a);
		m.print(res);
		
	}
}
