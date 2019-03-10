import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/*
 * @lc app=leetcode id=726 lang=java
 *
 * [726] Number of Atoms
 *
 * https://leetcode.com/problems/number-of-atoms/description/
 *
 * algorithms
 * Hard (44.12%)
 * Total Accepted:    9.4K
 * Total Submissions: 21.2K
 * Testcase Example:  '"H2O"'
 *
 * Given a chemical formula (given as a string), return the count of each
 * atom.
 * 
 * An atomic element always starts with an uppercase character, then zero or
 * more lowercase letters, representing the name.
 * 
 * 1 or more digits representing the count of that element may follow if the
 * count is greater than 1.  If the count is 1, no digits will follow.  For
 * example, H2O and H2O2 are possible, but H1O2 is impossible.
 * 
 * Two formulas concatenated together produce another formula.  For example,
 * H2O2He3Mg4 is also a formula.  
 * 
 * A formula placed in parentheses, and a count (optionally added) is also a
 * formula.  For example, (H2O2) and (H2O2)3 are formulas.
 * 
 * Given a formula, output the count of all elements as a string in the
 * following form: the first name (in sorted order), followed by its count (if
 * that count is more than 1), followed by the second name (in sorted order),
 * followed by its count (if that count is more than 1), and so on.
 * 
 * Example 1:
 * 
 * Input: 
 * formula = "H2O"
 * Output: "H2O"
 * Explanation: 
 * The count of elements are {'H': 2, 'O': 1}.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 
 * formula = "Mg(OH)2"
 * Output: "H2MgO2"
 * Explanation: 
 * The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: 
 * formula = "K4(ON(SO3)2)2"
 * Output: "K4N2O14S4"
 * Explanation: 
 * The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
 * 
 * 
 * 
 * Note:
 * All atom names consist of lowercase letters, except for the first character
 * which is uppercase.
 * The length of formula will be in the range [1, 1000].
 * formula will only consist of letters, digits, and round parentheses, and is
 * a valid formula as defined in the problem.
 * 
 */
class Solution {
    public String countOfAtoms(String formula) {

        // Valid Token:
        // Starting character must be Capitalized
        // Token RegEx: ([A-Z][a-z]*[0-9]*)

        // Solution is to store the current atom name
        // into a StringBuilder as we iterate through the string

        // We call this a "Token"

        // The Suffix number of the Token (which could be multiple digits)
        // will be tracked via a separate StringBuilder "numberString"

        // These 2 StringBuilder(s) (aka. Buffers) will be flushed
        // and stored into "atomList" HashMap to keep track of the count
        // Then the 2 buffers will be replaced with empty ones for next "Token"


        // To handle Brackets,
        // we push current atomList HashMap into a Stack when the bracket begin
        // and use a new HashMap to keep count inside the bracket 

        // When the bracket is closed, we take the number following the bracket 
        // and multiply it to all the current count in atomList

        // Then we pop the bracketStack and merge the count of 2 HashMap by key

        if (formula.length() == 1) {
            return formula;
        }


        Stack<Map<String, Integer>> bracketStack = new Stack<Map<String, Integer>>();
        Map<String, Integer> atomList = new LinkedHashMap<String, Integer>();

        StringBuilder atom = new StringBuilder();
        StringBuilder numberString = new StringBuilder();

        for (int i = 0; i < formula.length(); i++) {

            char currChar = formula.charAt(i);
            boolean isLastChar = (i == formula.length() - 1);

            // isTokenTerminated is used to determine whether 
            // we should flush the 2 buffers in 
            // atom and numberString
            // and store the content into current atomList

            boolean isTokenTerminated = isLastChar;
            if (!isLastChar) {
                char nextChar = formula.charAt(i+1);
                
                isTokenTerminated |= Character.isUpperCase(nextChar);
                isTokenTerminated |= nextChar == '(';
                isTokenTerminated |= nextChar == ')';
            }

            if (Character.isLowerCase(currChar)) {

                atom.append(currChar);

            } else if (Character.isUpperCase(currChar)) {

                atom.append(currChar);

            } else if (Character.isDigit(currChar)) {

                numberString.append(currChar);

            } else if (currChar == '(') {

                bracketStack.push(atomList);
                atomList = new LinkedHashMap<String, Integer>();

            } else if (currChar == ')') {

                // Missing logic to handle chain bracket closing such as ")2)3))"
                // Need to fix
                // if (formula.charAt(i+1) != '(' && Character.isAlphabet(formula.charAt(i+1)))

                int multiplier = 1;
                if (!isTokenTerminated) {
                    i++;

                    while(i < formula.length() && Character.isDigit(formula.charAt(i))) {
                        numberString.append(formula.charAt(i));
                        i++;
                    }

                    multiplier = Integer.valueOf(numberString.toString());
                    numberString = new StringBuilder();
                }
                final int multiplierFinal = multiplier;

                Map<String, Integer> oldAtomList = bracketStack.pop();

                atomList.entrySet().stream()
                    .forEach(entry -> {
                        int valueToAdd = entry.getValue() * multiplierFinal;

                        if (oldAtomList.containsKey(entry.getKey())) {
                            valueToAdd += oldAtomList.get(entry.getKey());

                            oldAtomList.replace(entry.getKey(), valueToAdd);
                            
                        } else {
                            oldAtomList.put(entry.getKey(), valueToAdd);
                        }

                    });

                atomList = oldAtomList;

                isTokenTerminated = false;
            }

            if (isTokenTerminated) {

                String atomName = atom.toString();
                String countStr = numberString.toString();
                int atomCount = countStr.isEmpty() ? 1 : Integer.valueOf(countStr);

                if (!atomName.isEmpty()) {
                    if(atomList.containsKey(atomName)) {
                        atomList.replace(
                            atomName,
                            atomList.get(atomName) + atomCount
                        );
                    } else {
                        atomList.put(atomName, atomCount);
                    }
                }

                atom = new StringBuilder();
                numberString = new StringBuilder();
            }
        }

        return atomList.entrySet().stream()
            .sorted((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey()))
            .map(entry -> entry.getValue() > 1
                ? entry.getKey() + entry.getValue()
                : entry.getKey())
            .collect(Collectors.joining());
    }

    public static void main(String[] xargs) {
        Solution solution = new Solution();

        // System.out.println(solution.countOfAtoms("H20"));
        System.out.println(solution.countOfAtoms("O(O(O))"));
        System.out.println(solution.countOfAtoms("Mg(OH)2"));
        System.out.println(solution.countOfAtoms("K4(ON4(SO3)2)2"));
    }
}

