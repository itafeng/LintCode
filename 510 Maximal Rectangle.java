/**

Given a 2D boolean matrix filled with False and True, find the largest rectangle containing all True and return its area.

Example
Given a matrix:

[
  [1, 1, 0, 0, 1],
  [0, 1, 0, 0, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 1, 1, 1],
  [0, 0, 0, 0, 1]
]
return 6.

**/

public class Solution {
    /**
     * @param matrix a boolean 2D matrix
     * @return an integer
     */
    public int maximalRectangle(boolean[][] matrix) {
        // Write your code here
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int maxArea = 0;
        
        int[] hist = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                hist[j] = !matrix[i][j] ? 0 : hist[j] + 1;
            }   
            int area = getMaxRectangleArea(hist);
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }
    
    private int getMaxRectangleArea(int[] hist) {
        Stack<Integer> stk = new Stack<>();
        
        int maxArea = 0;
        
        for (int i = 0; i <= hist.length; ++i) {
            int height = i == hist.length ? 0 : hist[i];
            
            while (!stk.isEmpty() && height <= hist[stk.peek()]) {
                int h = hist[stk.pop()];
                int w = stk.isEmpty() ? i : i - stk.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stk.push(i);
        }
        return maxArea;
    }
}



/**
	Note:

	
**/