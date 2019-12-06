package algo;

public class PeakNumber {
	/**
	 * 12345643
	 * 
	 * 78654321
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 * @param max
	 * @return
	 */
	static int count=0;
	public  static int findPeak(int[] arr,int i,int j,int max)
	{
		count++;
		int mid = (i+j)/2;
		if(i>j)
			return max;
		if(i==j)
			return max>arr[i]?max:arr[i];
		
		if(arr[mid]>max)
			return findPeak(arr,mid+1,j,arr[mid]);
		else
			return findPeak(arr,i,mid-1,max);
	}
	
	public static void main(String[] args)
	{
		
		int[] arr={1,2,3,4,5,6,4,3};
		int peak=findPeak(arr,1,arr.length-1,arr[0]);
		System.out.println(peak+":"+count);
		
		count=0;
		int[] arr2={7,8,6,5,4,3,2,1};
		peak=findPeak(arr2,1,arr2.length-1,arr2[0]);
		System.out.println(peak+":"+count);
		
		count=0;
		int[] arr3={6,5,4,3,2,1};
		peak=findPeak(arr3,1,arr3.length-1,arr3[0]);
		
		count=0;
		int[] arr4={1,2,3,6,5,4,3};
		peak=findPeak(arr4,1,arr4.length-1,arr4[0]);
		System.out.println(peak+":"+count);
		
		count=0;
		int[] arr5={1,2,3,6};
		peak=findPeak(arr5,1,arr5.length-1,arr5[0]);
		System.out.println(peak+":"+count);
	}
}
