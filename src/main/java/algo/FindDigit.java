package algo;

public class FindDigit {
	static String find_numbers(int digit, int start, int end) {
        StringBuffer sb=new StringBuffer();
        for(int num=start;num<=end;num++){
            if(hasDigit(num,digit)) 
                sb.append(num+" ");
        }
        return sb.toString().trim();
    }

    static boolean hasDigit(int num,int digit){
    	
    	    num = Math.abs(num);
    	    if(num==digit)
    	    		return true;
        while (num != 0)
        {
            if (num%10 == digit)
               return true;
            num   = num /10;
        }
        return false;
    }
    
    public static void main(String[] args)
    {
    		System.out.println(find_numbers(3,5,23));
    		System.out.println(find_numbers(0,0,15)+"END");

    }
}
