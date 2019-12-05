package algo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class ModuloSum {
	
    static int sum(int[] array,int m)
    {
    		
    		int subArrayLen = 1;
    		long max=Integer.MIN_VALUE;
    		while(subArrayLen<=array.length) {
    			
	    		for(int index=0;index<array.length;index++){
	    			long sum=0;
	    			if(index+subArrayLen > array.length)
	    				continue;
	    			for(int offset=0;offset<subArrayLen;offset++){
	    				if(index+offset < array.length) {
	    					sum += array[index+offset];
	    					System.out.print(array[index+offset]+" ");
	    				}
	    				else { sum=0;
	    					continue;
	    				}
	    				
	    			}
	    			if(sum!=0 && sum%m > max)
	    				max=sum%m;
	    			System.out.print(", subarraylen="+subArrayLen+", sum="+sum+",max="+max);
	    			System.out.println();
	    			
	    		}
	    		subArrayLen++;
    		}
    	
    		return (int) max;
    }
    
    static int sum2(int[] array,int m)
    {
    		
    		long max=Integer.MIN_VALUE;
    		int start=0;
    		int end=0;
		long sum=0;

    			
	    		for(int index=0;index<array.length;index++){
	    			sum += array[index];
	    			
	    			if(sum%m > max) {
	    				max=sum%m;
	    				end = index;
	    			} else {
	    				start++;
	    			}
	    			System.out.print(", subarraylen="+(end-start+1)+", sum="+sum+",max="+max);
	    			System.out.println();
	    			
	    		}
    	
    		return (int) max;
    }
    
    static long sum2(long[] numbers, Long mod) {
        
        Long maxSoFar = (numbers[numbers.length-1] + numbers[numbers.length-2])%mod;
        maxSoFar = (maxSoFar > (numbers[0]%mod)) ? maxSoFar : numbers[0]%mod;
        numbers[0] %=mod;
        for (Long i = 1L; i < numbers.length; i++) {
            long currentNumber = numbers[i.intValue()]%mod;            
            maxSoFar = maxSoFar > currentNumber ? maxSoFar : currentNumber;
            numbers[i.intValue()] = (currentNumber + numbers[i.intValue()-1])%mod;
            maxSoFar = maxSoFar > numbers[i.intValue()] ? maxSoFar : numbers[i.intValue()];
        }
        
        if(mod.equals(maxSoFar+1) || numbers.length == 2){
            return maxSoFar;
        }
        
        long previousNumber = numbers[0];
        TreeSet<Long> set = new TreeSet<>();
        set.add(previousNumber);
 
        for (Long i = 2L; i < numbers.length; i++) {
            Long currentNumber = numbers[i.intValue()];
            Long ceiling = set.ceiling(currentNumber);
            if(ceiling == null){
                set.add(numbers[i.intValue()-1]);            
                continue;
            }
            
            if(ceiling.equals(currentNumber)){
                set.remove(ceiling);
                Long greaterCeiling = set.ceiling(currentNumber);
                if(greaterCeiling == null){
                    set.add(ceiling);
                    set.add(numbers[i.intValue()-1]);            
                    continue;
                }
                set.add(ceiling);                    
                ceiling = greaterCeiling;
            }
            Long newMax = (currentNumber - ceiling + mod);
            maxSoFar = maxSoFar > newMax ? maxSoFar :newMax;
            set.add(numbers[i.intValue()-1]);            
        }
                
        return maxSoFar;
                
    }
    
    public static void main(String[] args){
    		int result=sum2(new int[]{3,3,9,9,5},7);
    		System.out.println("final result:"+result);
    		
    		/*50 1804289384
846930887 1681692778 1714636916 1957747794 424238336 719885387 1649760493 596516650 1189641422 
1025202363 1350490028 783368691 1102520060 2044897764 1967513927 1365180541 1540383427 304089173 1303455737 
35005212 521595369 294702568 1726956430 336465783 861021531 278722863 233665124 2145174068 468703136 1101513930 
1801979803 1315634023 635723059 1369133070 1125898168 1059961394 2089018457 628175012 1656478043 1131176230 
1653377374 859484422 1914544920 608413785 756898538 1734575199 1973594325 149798316 2038664371 1129566414
*/
    		//result=sum(new int[]{846930887, 1681692778, 1714636916, 1957747794, 424238336, 719885387 },1804289384);
		
    		/*result=sum(new int[]{846930887, 1681692778, 1714636916, 1957747794, 424238336, 719885387, 1649760493, 596516650, 1189641422 ,
    				1025202363, 1350490028, 783368691, 1102520060, 2044897764, 1967513927, 1365180541, 1540383427, 304089173, 1303455737, 
    				35005212, 521595369, 294702568, 1726956430 ,336465783, 861021531, 278722863, 233665124, 2145174068, 468703136, 1101513930, 
    				1801979803, 1315634023, 635723059, 1369133070, 1125898168, 1059961394, 2089018457, 628175012, 1656478043 ,1131176230, 
    				1653377374, 859484422, 1914544920 ,608413785, 756898538, 1734575199, 1973594325, 149798316, 2038664371, 1129566414},1804289384);
    				*/
    		System.out.println("final result:"+result);
    }

}
