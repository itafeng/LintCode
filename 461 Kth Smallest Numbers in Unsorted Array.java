/**

Find the kth smallest numbers in an unsorted integer array.

Example
Given [3, 4, 1, 2, 5], k = 3, the 3rd smallest numbers are [1, 2, 3].

Challenge 
An O(nlogn) algorithm is acceptable, if you can do it in O(n), that would be great.

**/

class Solution {

    public int kthSmallest(int k, int[] nums) {
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
			while (i < j && nums[j] >= pivot) {
				--j;
			}
			nums[i] = nums[j];
			while (i < j && nums[i] <= pivot) {
				++i;
			}
			nums[j] = nums[i];
		}
		nums[i] = pivot;
		return i;
	}
	
};
    

/**
	Note:
	
	1. Quick select algorithm (Average running time O(N))
	2. Max Heap (O(NlogN))

**/