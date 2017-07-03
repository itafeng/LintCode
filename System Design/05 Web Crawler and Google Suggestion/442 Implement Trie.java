/**

Implement a trie with insert, search, and startsWith methods.

 Notice

You may assume that all inputs are consist of lowercase letters a-z.

Have you met this question in a real interview? Yes
Example
insert("lintcode")
search("code")
>>> false
startsWith("lint")
>>> true
startsWith("linterror")
>>> false
insert("linterror")
search("lintcode)
>>> true
startsWith("linterror")
>>> true
 
 
**/

/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
class TrieNode {
    // Initialize your data structure here.
    public HashMap<Character, TrieNode> dict = null;
    public boolean isCompleteWord = false;
    public TrieNode() {
        dict = new HashMap<Character, TrieNode>(); 
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curNode = root;
        for (char c : word.toCharArray()) {
            if (!curNode.dict.containsKey(c)) {
                curNode.dict.put(c, new TrieNode());
            }
            curNode = curNode.dict.get(c);
        }
        curNode.isCompleteWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode curNode = root;
        
        for (char c : word.toCharArray()) {
            if (!curNode.dict.containsKey(c)) {
                return false;
            }
            curNode = curNode.dict.get(c);
        }
        return curNode.isCompleteWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode curNode = root;
        
        for (char c : prefix.toCharArray()) {
            if (!curNode.dict.containsKey(c)) {
                return false;
            }
            curNode = curNode.dict.get(c);
        }
        return true;
    }
}

/**

		
**/