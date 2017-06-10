/**

There is a stone game.At the beginning of the game the player picks n piles of stones in a line.

The goal is to merge the stones in one pile observing the following rules:

At each step of the game,the player can merge two adjacent piles to a new pile.
The score is the number of stones in the new pile.
You are to determine the minimum of the total score.

Have you met this question in a real interview? Yes
Example
For [4, 1, 1, 4], in the best solution, the total score is 18:

1. Merge second and third piles => [4, 2, 4], score +2
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
    public int stoneGame(int[] A) {
        // Write your code here
        
        if (A == null || A.length < 2) {
            return 0;
        }
        
        int rows = A.length;
        int[][] dp = new int[rows][rows];
       
        int[] prefixSum = new int[rows + 1];
        
        for (int i = 1; i <= rows; ++i) {
            prefixSum[i] = prefixSum[i - 1] + A[i - 1];
        }
        
        boolean[][] computed = new boolean[rows][rows];
        
        return compute(A, 0, rows - 1, prefixSum, computed, dp);
        
    }
    
    private int compute(int[] A, int start, int end, int[] prefixSum, boolean[][] computed, int[][] dp) {
        if (computed[start][end]) {
            return dp[start][end];
        }
        
        if (start == end) {
            dp[start][end] = 0;
        }
        else {        
            dp[start][end] = Integer.MAX_VALUE;
            
            for (int k = start; k < end; ++k) {
                dp[start][end] = Math.min(dp[start][end], compute(A, start, k, prefixSum, computed, dp) + compute(A, k + 1, end, prefixSum, computed, dp) + prefixSum[end + 1] - prefixSum[start]);
            }
        }
        
        computed[start][end] = true;
        
        return dp[start][end];
    }
}

/**
	Note:
	
	Use recursion with memoization

**/