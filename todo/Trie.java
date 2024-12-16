class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

public class Trie {
    TrieNode rootNode = new TrieNode();

    void insert(String word) {
        TrieNode currentNode = rootNode;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }
            currentNode = currentNode.children[index];
        }
        currentNode.isEndOfWord = true;
    }

    boolean search(String word) {
        TrieNode currentNode = rootNode;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (currentNode.children[index] == null) {
                return false;
            }
            currentNode = currentNode.children[index];
        }
        return currentNode.isEndOfWord;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("appla");
        trie.insert("banana");

        System.out.println();
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("banana"));
        System.out.println(trie.search("banz"));
        System.out.println(trie.search("cat"));
    }
}