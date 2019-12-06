package algo;
import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class Histogram {
	public int largestRectangleArea(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
	 
		Stack<Integer> stack = new Stack<Integer>();
	 
		int max = 0;
		int i = 0;
	 
		while (i < height.length) {
			System.out.println(stack);
			
			//push index to stack when the current height is larger than the previous one
			if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
				//System.out.print("**height[i]:"+height[i]);
				//if(!stack.isEmpty())
					//System.out.println("**stack peek:"+height[stack.peek()]);
				stack.push(i);
				i++;
				//System.out.println("\nStack:"+stack);
			} else {
			//calculate max value when the current height is less than the previous one
				int p = stack.pop();
				int h = height[p];
				int w = stack.isEmpty() ? i : i - stack.peek() - 1;
				System.out.println("i="+i+",height:"+h+",width="+w);
				if(!stack.isEmpty())
					System.out.println("stack peek:"+stack.peek());
				max = Math.max(h * w, max);
			}
	 
		}
	 
		while (!stack.isEmpty()) {
			int p = stack.pop();
			int h = height[p];
			int w = stack.isEmpty() ? i : i - stack.peek() - 1;
			max = Math.max(h * w, max);
		}
	 
		return max;
	}
	
	@Test
	public void testHistogram()
	{
		Histogram h=new Histogram();
		int[] height={2,1,5,6,2,3};
		int res=h.largestRectangleArea(height);
		assertEquals(res,10);
	}
}
