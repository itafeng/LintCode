/**

Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

 Notice

You can not divide any item into small pieces.

Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.

**/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        
        if (A == null || A.length == 0 || m < 1) {
            return 0;
        }
        
        int[][] dp = new int[2][m + 1];
        
        for (int i = 1; i <= A.length; ++i) {
            for (int j = 1; j <= m; ++j) {
                dp[i % 2][j] = dp[(i - 1) % 2][j];
                if (j >= A[i - 1]) {
                    dp[i % 2][j] = Math.max(dp[i % 2][j], dp[(i - 1) % 2][j - A[i - 1]] + A[i - 1]);
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
