/**

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

**/

public class Solution {
    /**
     * @param n an integer
     * @return true if this is a happy number or false
     */
    public boolean isHappy(int n) {
        // Write your code here
        
        if (n == 1) {
            return true;
        }
        
        int slow = n;
        int fast = getDigitSquareSum(getDigitSquareSum(n));
        
        while (slow != fast) {
            slow = getDigitSquareSum(slow);
            if (slow == 1) {
                return true;
            }
            fast = getDigitSquareSum(getDigitSquareSum(fast));
        }
        
        return false;    
    }
    
    private int getDigitSquareSum(int n) {
        int sum = 0;
        
        while (n > 0) {
            int digit = n % 10;
            n /= 10;
            sum += digit * digit;
        }
        return sum;
    }
}


/**
	Note:
	
	1. Use hashmap to keep track of visited numbers, O(N) space
	2. Use Floyd circle detection to detect circle, O(1) space
	
**/