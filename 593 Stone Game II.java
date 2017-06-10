/**

There is a stone game.At the beginning of the game the player picks n piles of stones in a circle.

The goal is to merge the stones in one pile observing the following rules:

At each step of the game,the player can merge two adjacent piles to a new pile.
The score is the number of stones in the new pile.
You are to determine the minimum of the total score.

Have you met this question in a real interview? Yes
Example
For [1, 4, 4, 1], in the best solution, the total score is 18:

1. Merge second and third piles => [2, 4, 4], score +2
2. Merge the first two piles => [6, 4]，score +6
3. Merge the last two piles => [10], score +10
Other two examples:
[1, 1, 1, 1] return 8
[4, 4, 5, 9] return 43

**/

public class Solution {
    /**
     * @param A an integer array
     * @return an integer
     */
    public int stoneGame2(int[] A) {
        // Write your code here
        
        if (A == null || A.length < 2) {
            return 0;
        }
        
        int length = A.length;
        
        int[][] dp = new int[2 * length][2 * length];
        
        int[] prefixSum = new int[2 * length + 1];
        
        for (int i = 1; i <= 2 * length; ++i) {
            prefixSum[i] = prefixSum[i - 1] + A[(i - 1) % length];
        }
        
        for (int i = 0; i < length; ++i) {
            dp[i][i] = 0;
        }
        
        for (int len = 2; len <= length * 2; ++len) {
            for (int i = 0; i + len - 1 < length * 2; ++i) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; ++k) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + prefixSum[j + 1] - prefixSum[i]);
                }
            }
        } 

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < length; ++i) {
            minCost = Math.min(minCost, dp[i][i + length - 1]); 
        }

        return minCost;
    }
}

/**
	Note:
	
	Double the list length and enumerate the starting point

**/