package algo;
import java.util.*;
import java.util.Map.Entry;

public class TopKElements {
	
	public int[] freqNumbers(int[] input, int k)
	{
	    Map<Integer,Integer> map=new HashMap<>();
	    for(int num:input)
	    {
	        if(map.get(num)!=null) {
	            int freq=map.get(num);
	            map.put(num,freq+1);
	        }
	        else
	            map.put(num,1);
	           
	    }
	    
	   
	    
	    PriorityQueue<Map.Entry<Integer,Integer>> pq=new PriorityQueue<Entry<Integer, Integer>>(k,new Comparator<Map.Entry<Integer,Integer>>() {
	        

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
	    // 5 8 10
	    for(Map.Entry<Integer,Integer> entry:map.entrySet())
	    {
	    		
	       if(compareEntry(pq.peek(),entry) >0 && pq.size() >= k) {
	           pq.poll();
	       
	           pq.offer(entry);
	       } else if(pq.size() <k)
	           pq.offer(entry);
	        
	    }
	    
	    int[] result=new int[k];
	    for(int i=0;!pq.isEmpty();i++)
	    {
	        Map.Entry<Integer,Integer> pair=pq.poll();
	        result[i]=pair.getKey();
	    }
	    
	    return result;
	}

	public int compareEntry(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
		return o1.getValue().compareTo(o2.getValue());
	}


}
