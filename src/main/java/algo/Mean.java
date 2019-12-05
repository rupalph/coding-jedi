package algo;
import java.util.Collections;
import java.util.PriorityQueue;

public class Mean {

	private static final int CAPACITY = 10;

	public static int findMean(int[] arr)
	{
		PriorityQueue<Integer> maxHeap =new PriorityQueue<>(CAPACITY,Collections.reverseOrder());
		PriorityQueue<Integer> minHeap =new PriorityQueue<>(CAPACITY);

		for(int number:arr){
			if( !minHeap.isEmpty() && number > minHeap.peek())
				minHeap.offer(number);
			else 
				maxHeap.offer(number);

			if(maxHeap.size() > (minHeap.size() +1))
				minHeap.offer(maxHeap.poll());
			else if( minHeap.size() > (maxHeap.size()+1))
				maxHeap.offer(minHeap.poll());

		}
		System.out.println(maxHeap);
		System.out.println(minHeap);
		if(maxHeap.size()==minHeap.size())
			return maxHeap.peek() + minHeap.peek() /2;
		else if(maxHeap.size()>minHeap.size())
			return maxHeap.peek();
		else
			return minHeap.peek();

	}

	public static void main(String[] args)
	{
		int maximum=100;
		int minimum=1;
		int len=100000;
		int a[]=new int[len];
		for(int i=0;i<len;i++)
		{
			
			int randomNum = minimum + (int)(Math.random() * maximum); 
			a[i]=randomNum;
			System.out.print(randomNum+" ");
		}
		System.out.println();
		int mean = findMean(a);
		System.out.println(mean);
	}
}
