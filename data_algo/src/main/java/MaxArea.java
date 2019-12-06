/**
 * Created by rupalph on 8/3/19.
 */
public class MaxArea
{
    public int maxArea(int[] height) {

        //two pointers, prev and cur. find area between two, and reset pointer
        //when area is larger then prev

        int left=0;
        int right = height.length-1;
        int area = 0;

        while(right>left){
            int newArea = Math.min(height[left],height[right])*(right-left);

            if(newArea>area){
                area = newArea;
            }

            if(height[left]<height[right])
                left++;
            else
                right--;

        }
        return area;

    }

    public static void main(String[] args){
        MaxArea m = new MaxArea();
        System.out.println(m.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
