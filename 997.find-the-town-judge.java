import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * @lc app=leetcode id=997 lang=java
 *
 * [997] Find the Town Judge
 *
 * https://leetcode.com/problems/find-the-town-judge/description/
 *
 * algorithms
 * Easy (47.87%)
 * Total Accepted:    6.2K
 * Total Submissions: 13K
 * Testcase Example:  '2\n[[1,2]]'
 *
 * In a town, there are N people labelled from 1 to N.  There is a rumor that
 * one of these people is secretly the town judge.
 * 
 * If the town judge exists, then:
 * 
 * 
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * 
 * 
 * You are given trust, an array of pairs trust[i] = [a, b] representing that
 * the person labelled a trusts the person labelled b.
 * 
 * If the town judge exists and can be identified, return the label of the town
 * judge.  Otherwise, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: N = 2, trust = [[1,2]]
 * Output: 2
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: N = 3, trust = [[1,3],[2,3]]
 * Output: 3
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: N = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 * 
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: N = 3, trust = [[1,2],[2,3]]
 * Output: -1
 * 
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 * Output: 3
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= N <= 1000
 * trust.length <= 10000
 * trust[i] are all different
 * trust[i][0] != trust[i][1]
 * 1 <= trust[i][0], trust[i][1] <= N
 * 
 * 
 */
class Solution {

    /**
     * This solution trade memory complexity for runtime
     * Memory: O(n^2)
     * Runtime: O(2n) -> O(n)
     * 
     * TODO: Is there a better solution ?
     */

    public int findJudge(int N, int[][] trust) {

        if (N == 1) {
            return 1;
        }

        Set<Integer> trusterList = new HashSet<>();

        // Key: person being trusted
        // Value: list of people who trusted him
        Map<Integer, List<Integer>> townRelationShip = new LinkedHashMap<>();

        Arrays.stream(trust)
            .forEach(relationship -> {
                int truster = relationship[0];
                int trustee = relationship[1];

                if(townRelationShip.containsKey(trustee)) {
                    townRelationShip.get(trustee).add(truster);
                } else {
                    List<Integer> newTrusterList = new LinkedList<>();
                    newTrusterList.add(truster);
                    townRelationShip.put(trustee, newTrusterList);
                }

                trusterList.add(truster);
            });

        List<Integer> judgeList = townRelationShip.entrySet().stream()
            .filter(entry -> entry.getValue().size() == N - 1)
            .filter(entry -> !trusterList.contains(entry.getKey()))
            .map(entry -> entry.getKey())
            .collect(Collectors.toList());

        if (judgeList.size() == 1) {
            return judgeList.get(0);
        }

        return -1;
    }
}

