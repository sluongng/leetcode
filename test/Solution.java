import java.util.ArrayList;
import java.util.List;

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
                if (currentTrie.getNodes()[index] == null) {
                    currentTrie.getNodes()[index] = new Trie();
                }
                currentTrie = currentTrie.getNodes()[index];
            }

            // At end of traversal, assign the word to child node
            currentTrie.setWord(word);
        }

        List<String> result = new ArrayList<>();
        // Iterate over all elements of board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(board, i, j, myDict, result);
            }
        }

        return result;
    }

    /**
     * <Depth first search solution from leetcode>
     * we scan the board from 1 corner to the opposite corner[(0,0) -> (maxHeight, maxWidth)]
     * Duplication:
     * each time we scan, we temporarily replace content of current cell with a mark character 
     * which signify that it has been scanned to avoid circular navigation 
     * 
     * @param board
     * @param i
     * @param j
     * @param node
     * @param result
     */
    public void dfs(char[][] board, int i, int j, Trie dict, List<String> result) {
        char currChar = board[i][j];
        int currIdx = currChar - 'a';

        // Terminate if circular navigation on board
        // OR that there is no word found in dictionary Trie
        if (currChar == '#' || dict.getNodes()[currIdx] == null) {
            return;
        }
        Trie currNode = dict.getNodes()[currIdx];

        if (currNode.getWord() != null && !currNode.getWord().isEmpty()) {
            result.add(currNode.getWord());

            // we remove word out of dict 
            // tree to avoid it being matched again
            // This could also be avoid if we collect results using a Set instead of a List
            currNode.setWord(null);
        }

        // Mark cell on board to avoid circular navigation
        board[i][j] = '#';

        if (i > 0) dfs(board, i - 1, j, dict, result);
        if (j > 0) dfs(board, i, j - 1, dict, result);
        if (i < board.length - 1) dfs(board, i + 1, j, dict, result);
        if (j < board[i].length - 1) dfs(board, i, j + 1, dict, result);

        // Restore the marking
        board[i][j] = currChar;
    }

    public static void main(String[] args) {
        Solution mySol = new Solution();

        /*
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
         */
        char[][] board = {
          {'o','a','a','n'},
          {'e','t','a','e'},
          {'i','h','k','r'},
          {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        // for (int i = 0; i < board.length; i++) {
        //     for (int j = 0; j < board[i].length; j++) {
        //         System.out.print(board[i][j]);
        //     }
        //     System.out.println();
        // }
        // for (String w : words) {
        //     System.out.println(w);
        // }

        List<String> answer = mySol.findWords(board, words);
        answer.forEach(System.out::println);
    }
}

class Trie {
    // 26 character in the alphabets
    // index is calculated by `int index = currChar - 'a'`
    private Trie[] nodes = new Trie[26];
    private String word = "";

    public Trie() {
    }

    /**
     * @return the nodes
     */
    public Trie[] getNodes() {
        return nodes;
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
