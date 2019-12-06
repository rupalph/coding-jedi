import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rupalph on 11/1/19.
 */
public class PhoneNumberCombinations {

    static void printCombinations(String s, int i, String prefix, Map<Integer,List<Character>> map){
        if(i==s.length()) { System.out.println(prefix); return; }

        int num = Integer.parseInt(s.charAt(i)+"");
        for(char c: map.get(num)){
            printCombinations(s, i+1, prefix+c, map);
        }
    }

    public static void main(String[] args){
        Map<Integer, List<Character>> map = new HashMap<>();
        map.put(1, Arrays.asList('a','b','c'));
        map.put(2, Arrays.asList('d','e','f'));
        map.put(3, Arrays.asList('g','h','i'));


        printCombinations("123", 0, "", map );
    }
}
