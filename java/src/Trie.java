import java.util.HashMap;
import java.util.Map;

/**
 * Created by rupalh on 5/23/18.
 */
class Trie {

    /** Initialize your data structure here. */
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;

        for(char c:word.toLowerCase().toCharArray()){
            if(node.get(c)!=null) {
                node = node.get(c);
            }
            else {
                node.put(c,new TrieNode());
                node = node.get(c);
            }
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for(char c:word.toLowerCase().toCharArray()){
            if(node.get(c)!=null)
                node = node.get(c);
            else
                return false;
        }
        if(node.isEnd)
            return true;
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char c:prefix.toLowerCase().toCharArray()){
            if(node.get(c)!=null)
                node = node.get(c);
            else
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        Trie trie = new Trie();
        trie.insert("cat");
        trie.insert("cats");
        trie.insert("caterpiller");

        System.out.println(trie.search("cat"));
        System.out.println(trie.startsWith("cat"));
        System.out.println(trie.search("can"));


    }
}

class TrieNode {
    Map<Character,TrieNode> charMap = new HashMap<>();
    boolean isEnd = false;
    public void put(char c, TrieNode tri){
        charMap.put(c,tri);
    }
    public TrieNode get(char key){
        return charMap.get(key);
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
