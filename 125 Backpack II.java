/**

Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?

 Notice

You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Have you met this question in a real interview? Yes
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.


**/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        
        int[][] dp = new int[2][m + 1];
        
        for (int i = 1; i <= A.length; ++i) {
            for (int j = 1; j <= m; ++j) {
                dp[i % 2][j] = dp[(i - 1) % 2][j];
                
                if (j >= A[i - 1]) {
                    dp[i % 2][j] = Math.max(dp[i % 2][j], dp[(i - 1) % 2][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        
        return dp[A.length % 2][m];
    }
}

/**
	Note:
	
	Rolling array optimization
	
**/
