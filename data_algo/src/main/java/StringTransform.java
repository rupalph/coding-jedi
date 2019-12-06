import java.util.*;

/**
 * Created by rupalph on 10/7/19.
 */
public class StringTransform {

    /*
     * Complete the function below.
     */

    static class Node {
        String word;
        Node parent;
        Node(String s) { word = s; }
    }
    static String[] string_transformation(String[] words, String start, String stop) {
        List<String> result = new ArrayList<>();

        Set<String> set = wordsToSet(words);

        Map<String,Set<String>> cache = new HashMap<>();
        set.add(stop);
        Set<String> visited = new HashSet<>();
        Queue<Node> q = new LinkedList<>();

        Node head = new Node(start);
        Node tail = null;
        q.offer(head);
        boolean found = false;
        while(!q.isEmpty()) {
            Node node = q.poll();
            String word = node.word;
            visited.add(word);
            if(word.equals(stop)) { tail = node; found = true; break; }
            for(String n:getNextWords(word,set,cache)){
                if(!visited.contains(n)) {
                    Node nextNode = new Node(n);
                    nextNode.parent = node;
                    tail = nextNode;
                    q.offer(nextNode);

                }
            }

        }
        if(!found) {
            result.add("-1");
        }
        else {
            result = tracePath(tail);
            if(result.size()<2){
                result.clear();
                result.add("-1");
            }
        }
        return result.toArray(new String[0]);

    }

    static Set<String> wordsToSet(String[] words){
        Set<String> res = new HashSet<>();
        for(String w:words)
            res.add(w);
        return res;
    }
    static List<String> tracePath(Node tail) {
        List<String> res = new ArrayList<>();
        Node cur = tail;
        while(cur!=null){
            res.add(cur.word);
            cur = cur.parent;
        }
        Collections.reverse(res);
        return res;
    }
    static Set<String> getNextWords(String word,Set<String> words,Map<String,Set<String>> cache) {
        if(cache.containsKey(word)) return cache.get(word);
        Set<String> res = new HashSet<>();

        //replace or update
        for(int j=0;j<word.length();j++){
            for(int i=0;i<26;i++){
                char c=(char)('a'+i);
                int start = j>0?j-1:0;
                String newWord = word.substring(0,start)+c+word.substring(j+1,word.length());
                if(words.contains(newWord)) res.add(newWord);
            }
        }

        //insert
        for(int j=0;j<word.length();j++){
            for(int i=0;i<26;i++){
                char c=(char)('a'+i);
                String newWord = word.substring(0,j)+c+word.substring(j+1,word.length());
                if(words.contains(newWord)) res.add(newWord);
            }
        }

        //delete
        for(int j=0;j<word.length();j++){
            for(int i=0;i<26;i++){
                int start = j>0?j-1:0;
                String newWord = word.substring(0,start)+word.substring(j+1,word.length());
                if(words.contains(newWord)) res.add(newWord);
            }
        }
        cache.put(word,res);
        return res;

    }


    public static void main(String[] args){
//        String[] res = string_transformation(new String[]{"cat", "hat", "bad", "had"}, "bat", "had");
//        System.out.println(Arrays.toString(res));

        String[] res2 = string_transformation(new String[]{"cccb","accc","accb"},"cccc","cccw");
        System.out.println(Arrays.toString(res2));

        String[] res3 = string_transformation(new String[]{"a","b","c"},"a","d");
        System.out.println(Arrays.toString(res3));

        String[] res4 = string_transformation(new String[]{},"a","d");
        System.out.println(Arrays.toString(res4));

        String[] res5 = string_transformation(new String[]{},"a","a");
        System.out.println(Arrays.toString(res5));
    }
}
