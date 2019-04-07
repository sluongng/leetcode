import sun.print.resources.serviceui;

/*
 * @lc app=leetcode id=212 lang=java
 *
 * [212] Word Search II
 *
 * https://leetcode.com/problems/word-search-ii/description/
 *
 * algorithms
 * Hard (27.96%)
 * Total Accepted:    104.5K
 * Total Submissions: 373.6K
 * Testcase Example:  '[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]\n["oath","pea","eat","rain"]'
 *
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: 
 * board = [
 * ⁠ ['o','a','a','n'],
 * ⁠ ['e','t','a','e'],
 * ⁠ ['i','h','k','r'],
 * ⁠ ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 * 
 * Output: ["eat","oath"]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 * 
 * 
 */
class Solution {
    public List<String> findWords(char[][] board, String[] words) {

        Trie myDict = new Trie();
        
        // Iterate over words and create a Trie structure
        for (String word : words) {
            // pointer trie set to root node
            Trie currentTrie = myDict;
            
            // Traverse down the tree according to characters of current word
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (currentTrie.getChild()[index] == null) {
                    currentTrie.getChild()[index] = new Trie();
                }
                currentTrie = currentTrie.getChild()[index];
            }

            // At end of traversal, assign the word to child node
            currentTrie.setWord(word);
        }

        // Iterate over all elements of board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int childIdx = board[i][j] - 'a';
            }
        }
    }
}

class Trie {
    // 26 character in the alphabets
    // index is calculated by `int index = currChar - 'a'`
    private Trie[] child = new Trie[26];
    private String word = "";

    public Trie() {
    }

    /**
     * @return the child
     */
    public Trie[] getChild() {
        return child;
    }

    /**
     * @param child the child to set
     */
    public void setChild(Trie[] child) {
        this.child = child;
    }
    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }
    
    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }
}

