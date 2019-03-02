import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @lc app=leetcode id=17 lang=java
 *
 * [17] Letter Combinations of a Phone Number
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * algorithms
 * Medium (40.37%)
 * Total Accepted:    347.6K
 * Total Submissions: 861.1K
 * Testcase Example:  '"23"'
 *
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below. Note that 1 does not map to any letters.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 
 * Note:
 * 
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 * 
 */
class Solution {

    private static Map<Integer, List<String>> numpadLetters = new LinkedHashMap<>();

    static {
        numpadLetters.put(2, Arrays.asList("a", "b", "c"));
        numpadLetters.put(3, Arrays.asList("d", "e", "f"));
        numpadLetters.put(4, Arrays.asList("g", "h", "i"));
        numpadLetters.put(5, Arrays.asList("j", "k", "l"));
        numpadLetters.put(6, Arrays.asList("m", "n", "o"));
        numpadLetters.put(7, Arrays.asList("p", "q", "r", "s"));
        numpadLetters.put(8, Arrays.asList("t", "u", "v"));
        numpadLetters.put(9, Arrays.asList("w", "x", "y", "z"));
    }

    public List<String> letterCombinations(String digits) {
        
        return digits.chars()
            .mapToObj(c -> (char)c)
            .map(Character::getNumericValue)
            .map(numpadLetters::get)
            .reduce((result, list) -> result == null
                ? list
                : merge(result, list)
            )
            .orElse(new LinkedList<String>());
    }

    private List<String> merge(List<String> base, List<String> result) {
        return base.stream()
            .map(str -> result.stream()
                .map(letter -> str + letter)
                .collect(Collectors.toList())
            )
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }
}

