/**
Given an array of n * m matrix, and a moving matrix window (size k * k), move the window from top left to botton right at each iteration, find the maximum sum inside the window at each moving.
Return 0 if the answer does not exist.


Example
For matrix

[
  [1, 5, 3],
  [3, 2, 1],
  [4, 1, 9],
]
The moving window size k = 2. 
return 13.

At first the window is at the start of the array like this

[
  [|1, 5|, 3],
  [|3, 2|, 1],
  [4, 1, 9],
]
,get the sum 11;
then the window move one step forward.

[
  [1, |5, 3|],
  [3, |2, 1|],
  [4, 1, 9],
]
,get the sum 11;
then the window move one step forward again.

[
  [1, 5, 3],
  [|3, 2|, 1],
  [|4, 1|, 9],
]
,get the sum 10;
then the window move one step forward again.

[
  [1, 5, 3],
  [3, |2, 1|],
  [4, |1, 9|],
]
,get the sum 13;
SO finally, get the maximum from all the sum which is 13.


**/ 

public class Solution {
    /**
     * @param matrix an integer array of n * m matrix
     * @param k an integer
     * @return the maximum number
     */
    public int maxSlidingMatrix(int[][] matrix, int k) {
        // Write your code here
        if (matrix == null) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        if (m == 0 || n == 0 || m < k || n < k) {
            return 0;
        }
        
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                sum[i][j] = matrix[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }
        
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = k; i <= m; ++i) {
            for (int j = k; j <= n; ++j) {
                int curSum = sum[i][j] - sum[i][j - k] - sum[i - k][j] + sum[i - k][j - k];
                maxSum = Math.max(maxSum, curSum);
            }
        }
        
        return maxSum;
    }
}

/**
	Pre-compute the prefix sum array.
	
	Sum of sub-matrix ending at (i, j) = sum[i][j] - sum[i - k][j] - sum[i][j - k] + sum[i - k][j - k] (i, j 1-based)
	
**/