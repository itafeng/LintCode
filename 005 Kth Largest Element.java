/**

Find K-th largest element in an array.

 Notice

You can swap elements in the array

Example
In array [9,3,2,4,8], the 3rd largest element is 4.

In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.

Challenge 
O(n) time, O(1) extra memory.

**/

class Solution {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
    

    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        int pos = partition(nums, start, end);
        
        if (pos - start + 1 == k) {
            return nums[pos];
        }
        else if (pos - start + 1 > k) {
            return quickSelect(nums, start, pos - 1, k);
        }
        else {
            return quickSelect(nums, pos + 1, end, k - (pos - start + 1));
        }
    }
    
    private int partition(int[] nums, int start, int end) {
        int i = start, j = end;
        int pivot = nums[start];
        
        while (i < j) {
            while (i < j && nums[j] <= pivot) {
                --j;
            }
            nums[i] = nums[j];
            
            while (i < j && nums[i] >= pivot) {
                ++i;
            }
            nums[j] = nums[i];
        }
        nums[i] = pivot;
        return i; 
    }
 
 }   
    

/**
	Note:
	
	1. Quick select algorithm (Average running time O(N))
	2. Max Heap (O(NlogN))

**/