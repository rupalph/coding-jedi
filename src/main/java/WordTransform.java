import java.util.*;

/**
 * Created by rupalph on 6/16/19.
 */
public class WordTransform {
    char alphabet[] = new char[26];

    public WordTransform() {
        for (char c = 'a'; c <= 'z'; c++) {
            alphabet[c - 'a'] = c;
        }

    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;

        int hops = 0;
        for(int j=0;j<wordList.size();j++) {
            for (int i = 0; i < beginWord.length(); i++) {
                String w = findPos(beginWord, i, wordList);
                if (!w.isEmpty()) {
                    beginWord = beginWord.replace(beginWord.charAt(i), w.charAt(i));
                    System.out.println(beginWord);
                    hops++;
                    i=0;
                    if(beginWord.equals(endWord))
                        return hops;
                }
            }


        }
        return 0;
    }

    private String findPos(String word, int i, List<String> wordList) {
        for (String w : wordList) {

            if (word.charAt(i) != w.charAt(i) && word.substring(0, i).equals(w.substring(0, i)) && word.substring(i + 1, word.length()).equals(w.substring(i + 1, w.length())))
                return w;
        }
        return "";
    }


    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Map<String,Boolean> map = new HashMap<>();

        for(String w:wordList){
            map.put(w,true);

        }
        if(!map.containsKey(endWord))
            return 0;

        Queue<String> q = new LinkedList<>();

        q.offer(beginWord);

        int hops=1;
        while(!q.isEmpty()){
            int len = q.size();
            for(int i=0;i<len;i++) {
                String w = q.poll();

                System.out.println("finding neighbors of " + w);
                findNeighbors(q, w, map);

                if (w.equals(endWord))
                    return hops;

                map.remove(w);
            }
            hops++;
        }

        return 0;
    }

    private void findNeighbors(Queue<String> q, String w, Map<String, Boolean> map) {


        for(int i=0;i<w.length();i++){
            String left = w.substring(0,i);
            String right = w.substring(i+1);
            for(int j=0;j<26;j++){
                String newWord = left + alphabet[j] + right;
                if(map.containsKey(newWord) && !w.equals(newWord)) {
                    System.out.println(newWord);
                    q.offer(newWord);
                    map.remove(newWord);
                }
            }
        }
    }

    public static void main(String[] args){
        String w1 = "hot";
        List<String> list = Arrays.asList("hog","fog","hit", "dot", "dig", "dog","fig","pig","peg");

        WordTransform wt = new WordTransform();
        int hops = wt.ladderLength2(w1,"peg", list);

        System.out.println(hops);
    }
}
