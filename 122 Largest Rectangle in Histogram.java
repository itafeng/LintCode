/**

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given heights = [2,1,5,6,2,3],
return 10.

**/

public class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> index = new Stack<>();
        int maxArea = 0;
        
        for (int i = 0; i <= heights.length; ++i) {
            int height = i == heights.length ? 0 : heights[i];
            
            while (!index.isEmpty() && height <= heights[index.peek()]) {
                int h = heights[index.pop()];
                int w = index.isEmpty() ? i : i - index.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            index.push(i);
        }
        return maxArea;
    }
}



/**
	Note:
	
	1) For the ease of processing, add an artificial 0 to the end of the height array so all elements in the stack can be popped out at the end
	2) Use single stack 
		
**/