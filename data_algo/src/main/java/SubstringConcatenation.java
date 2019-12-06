import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
/**
 * Created by rupalph on 8/7/19.
 */
public class SubstringConcatenation {

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        if(s.length()==0 || words.length==0)
            return result;
        Map<String,Integer> freq = new HashMap<>();
        Map<String,Integer> wordsSeen = new HashMap<>();

        for(String w:words){
            freq.put(w, freq.getOrDefault(w,0)+1);
        }
        int start=0;int cur=0;

        /*
        1 start index, get word of length words[0] - compare if equal if equal increment match, check for next word starting
        at cur += words[0].length

        2 repeat above till we find match or reach end of string,


        3 if at any point do not find match break the loop from 1 to 2

        repeat step 1 to 3  at start + 1 index
         */

        for(start=0;start<s.length();start++){
            int i=0;
            wordsSeen.putAll(freq);
            for(cur=start;i<words.length&&cur+words[i].length()<=s.length();cur+=words[i-1].length()){
                String word = s.substring(cur,cur+words[i].length());
                System.out.print(":"+cur+":"+word+":");

                if(wordsSeen.containsKey(word) && wordsSeen.get(word)>0) {
                    wordsSeen.put(word,wordsSeen.getOrDefault(word,1)-1);
                    i++;
                }
                else
                    break;

                if(i==words.length)
                    result.add(start);
            }
            System.out.println();
        }

        return result;
    }

    @Test
    public void test1(){
       String s = "barfoothefoobarman";
       String[] words = new String[]{"foo","bar"};

       SubstringConcatenation substringConcatenation = new SubstringConcatenation();
       System.out.println(substringConcatenation.findSubstring(s,words));

    }

    @Test
    public void test2(){

        String s = "wordgoodgoodgoodbestword";
        String[] words = new String[]{"word","good","best","word"};

        SubstringConcatenation substringConcatenation = new SubstringConcatenation();
        System.out.println(substringConcatenation.findSubstring(s,words));

    }

    @Test
    public void test3(){

        String s = "whymebestwordgoodwordgoodbestword";
        String[] words = new String[]{"word","good","best","word"};

        SubstringConcatenation substringConcatenation = new SubstringConcatenation();
        System.out.println(substringConcatenation.findSubstring(s,words));

    }
}
