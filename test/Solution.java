import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;
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

            if (currChar != '(' && currChar != ')') {

                if (Character.isLowerCase(currChar)) {
                    atom.append(currChar);

                } else if (Character.isUpperCase(currChar)) {

                    atom.append(currChar);

                } else if (Character.isDigit(currChar)) {

                    numberString.append(currChar);
                }

                boolean isTokenTerminated = isLastChar;
                if (!isLastChar) {
                    char nextChar = formula.charAt(i + 1);

                    isTokenTerminated |= Character.isUpperCase(nextChar);
                    isTokenTerminated |= nextChar == '(';
                    isTokenTerminated |= nextChar == ')';
                }

                if (isTokenTerminated) {

                    String atomName = atom.toString();
                    String countStr = numberString.toString();
                    int atomCount = countStr.isEmpty() ? 1 : Integer.valueOf(countStr);

                    if (!atomName.isEmpty()) {
                        if (atomList.containsKey(atomName)) {
                            atomList.replace(atomName, atomList.get(atomName) + atomCount);
                        } else {
                            atomList.put(atomName, atomCount);
                        }
                    }

                    atom = new StringBuilder();
                    numberString = new StringBuilder();
                }
            } else if (currChar == '(') {

                bracketStack.push(atomList);
                atomList = new LinkedHashMap<String, Integer>();

            } else if (currChar == ')') {

                if (isLastChar) {
                    continue;
                } else {
                    while (i + 1 < formula.length() && Character.isDigit(formula.charAt(i + 1))) {
                        numberString.append(formula.charAt(i + 1));
                        i++;
                    }

                    String multiplierStr = numberString.toString();
                    numberString = new StringBuilder();

                    int multiplier = "".equals(multiplierStr) ? 1 : Integer.valueOf(multiplierStr);

                    atomList = atomList.entrySet().stream()
                            .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() * multiplier));

                    if (!bracketStack.empty()) {
                        for (Entry<String, Integer> entry : bracketStack.pop().entrySet()) {

                            if (atomList.containsKey(entry.getKey())) {
                                atomList.replace(entry.getKey(), entry.getValue() + atomList.get(entry.getKey()));
                            } else {
                                atomList.put(entry.getKey(), entry.getValue());
                            }
                        }
                    }
                }

            }
        }

        return atomList.entrySet().stream().sorted((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey()))
                .map(entry -> entry.getValue() > 1 ? entry.getKey() + entry.getValue() : entry.getKey())
                .collect(Collectors.joining());
    }

    public static void main(String[] xargs) {
        Solution solution = new Solution();

        Map<String, String> testCaseMap = new LinkedHashMap<>();
        testCaseMap.put("H2O", "H2O");
        testCaseMap.put("Mg(OH)2", "H2MgO2");
        testCaseMap.put("K4(ON4(SO3)2)2", "K4N8O14S4");
        testCaseMap.put("((N42)24(OB40Li30CHe3O48LiNN26)33(C12Li48N30H13HBe31)21(BHN30Li26BCBe47N40)15(H5)16)14",
        "B18900Be18984C4200H5446He1386Li33894N50106O22638");
        testCaseMap.put(
                "(Db40Hs6Sc10I28IrBe22)8((Np25Lu36Ge46)46(Ta28Lv16Fe38Sn11PbCa23Ta37)26(Li35Hf11I38Po43Sg10Tl19BrZn24)19(Au3CePa23Ac36Hs7Es35AlTlGdRa7)34(Nb38Cu7Cd14)16)13",
                "Ac15912Al442Au1326Be176Br247Ca7774Cd2912Ce442Cu1456Db320Es15470Fe12844Gd442Ge27508Hf2717Hs3142I9610Ir8Li8645Lu21528Lv5408Nb7904Np14950Pa10166Pb338Po10621Ra3094Sc80Sg2470Sn3718Ta21970Tl5135Zn5928");

        testCaseMap.entrySet().stream().forEach(entry -> {
            String atomCount = solution.countOfAtoms(entry.getKey());

            if (entry.getValue().equals(atomCount)) {
                System.out.println("Test Passed!");
                System.out.println("---");
            } else {
                System.out.println("Test Failed!");
                System.out.println("Expected: " + entry.getValue());
                System.out.println("Actual: " + atomCount);
                System.out.println("---");
            }
        });
    }
}
