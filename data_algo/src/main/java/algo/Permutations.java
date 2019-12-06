package algo;
import java.util.ArrayList;
import java.util.List;

public class Permutations {
	List<String> permutations=new ArrayList<String>();
	public void findPermutations(String str,int start,int end){
		if(start==end)
			permutations.add(str);
		String tmp;
		for(int i=start;i<end;i++){
			tmp=str;
			str=swap(str,start, i);
			findPermutations(str, start+1, end);
	        str=tmp;
		}
	}
	
	private String swap(String str, int start, int i) {
		char[] arr=str.toCharArray();
		char tmp=arr[start];
		arr[start]=arr[i];
		arr[i]=tmp;
		return new String(arr);
	}

	public static void main(String[] args){
		Permutations pe=new Permutations();
		pe.findPermutations("abc",0,3);
		
		System.out.println(pe.permutations);
	}
}
