/**

There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

 Notice

All costs are positive integers.


Example
Given n = 3, k = 3, costs = [[14,2,11],[11,14,5],[14,3,10]] return 10

house 0 is color 2, house 1 is color 3, house 2 is color 2, 2 + 5 + 3 = 10
**/
 
public class Solution {
    /**
     * @param costs n x k cost matrix
     * @return an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        // Write your code here
        
        if (costs == null || costs.length == 0) {
            return 0;
        }
        
        int n = costs.length;
        int m = costs[0].length;
        int[][] dp = new int[2][m];
        
        dp[1] = costs[0];
        
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j < m; ++j) {
                dp[i % 2][j] = Integer.MAX_VALUE;
                
                for (int k = 0; k < m; ++k) {
                    if (k == j) {
                        continue;
                    }
                    dp[i % 2][j] = Math.min(dp[i % 2][j], dp[(i - 1) % 2][k]);
                }
                dp[i % 2][j] += costs[i - 1][j];
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; ++i) {
            ans = Math.min(ans, dp[n % 2][i]);
        }
        
        return ans;
    }
}

/**
	Note:
	
	dp[i][j] depicts minimum cost of painting i houses with color j

**/