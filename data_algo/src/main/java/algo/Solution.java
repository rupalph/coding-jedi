package algo;
import java.util.*;

/*
 * Complete the function below.
 */
class WordInfo {
	int freq;
	List<Integer> positions=new ArrayList<>();
	public WordInfo(int f,int pos){
		freq=f;
		positions.add(pos);
	}
}
public class Solution {
	/*
	 * Complete the function below.
	 */

	static final int MAX_LEN = 1000000;
	static String[] missingWords(String s, String t) {
		//Assuming if contraints are not met, return empty array
		List<String> result=new ArrayList<>();
		if(t.length()>=s.length() || (t.length()>MAX_LEN || s.length()> MAX_LEN))
			return result.toArray(new String[0]);
		//Build freq map from the small string
		Map<String,Integer> map=new HashMap<>();
		for(String word:t.split(" ")){
			if(word.length()>=15)
				return result.toArray(new String[0]);
			if(map.get(word)!=null){
				int freq=map.get(word);
				map.put(word,freq+1);

			}
			else
				map.put(word,1);
		}
		//Now, scan the String and check if word is in map
		//if found reduce the frequency otherwise insert in result.
		Map<String,List<Integer>> missingPositions=new HashMap<String,List<Integer>>();
		int i=0;
		for(String word:s.split(" ")){

			if(word.length()>=15) {
				result.clear();
				return result.toArray(new String[0]);
			}
			if(map.get(word)!=null){

				int freq=map.get(word);
				if(freq>0) {
					if(freq-1 ==0) {
						map.remove(word);
						missingPositions.remove(word);
					}
					map.put(word,freq-1);

					addMe(missingPositions,word,i);
				} 
				else {
					result.add(word);
					addMe(missingPositions,word,i);
				}
			}
			else {
				result.add(word);
				addMe(missingPositions,word,i);
			}
			i++;

		}
		Iterator<String> it=result.iterator();
		while(it.hasNext()) {
			String next=it.next();
			if(missingPositions.get(next)!=null){
				List<Integer> pos=missingPositions.get(next);
				System.out.println(pos);
			}
		}
		return result.toArray(new String[0]);

	}

	static void addMe(Map<String,List<Integer>> map,String key,int val)
	{
		List<Integer> list=map.get(key);
		if(list!=null)
		{
			list.add(val);
		}
		else
		{
			list=new ArrayList<Integer>();
			list.add(val);
			map.put(key, list);
		}
	}

	static List<String> missingWords2(String s, String t) {

		List<String> missing = new ArrayList<>();
		String[] a = s.split(" ");
		String[] b = t.split(" ");
		int i=0,j=0;
		for (i = 0, j = 0; i < a.length && j < b.length; i++) {
			//System.out.println("a[i]="+a[i]+",b[j]="+b[j]);
			if (!a[i].equals(b[j])) {
				missing.add(a[i]);
			} else {
				j++;
			}
			
		}
		for(int r=i;r<a.length;r++)
			missing.add(a[r]);
		return missing;
	}
	public static void main(String[] args)
	{
		String[] out=missingWords("I am using hackerrank practice test to improve programming practice",
				"am hackerrank to improve practice");
		for(String o:out)
			System.out.println(o);
		
		System.out.println(missingWords2("I am using hackerrank practice test to improve programming practice",
				"am hackerrank to improve practice"));
	}

}