/**

You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in forward order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.

Example
Given 6->1->7 + 2->9->5. That is, 617 + 295.

Return 9->1->2. That is, 912.


**/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;      
 *     }
 * }
 */
public class Solution {
    /**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2 
     */
    public ListNode addLists2(ListNode l1, ListNode l2) {
        // write your code here
        ListNode dummy = new ListNode(0);
        ListNode last = dummy;
        
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            carry = sum / 10;
            sum %= 10;
            
            ListNode cur = new ListNode(sum);
            last.next = cur;
            last = cur;
        }
        
        if (carry > 0) {
            last.next = new ListNode(carry);
        }
        
        dummy.next = reverseList(dummy.next);
        
        return dummy.next;
        
    }  
    
    private ListNode reverseList(ListNode list) {
        if (list == null || list.next == null) {
            return list;
        }
        
        ListNode prev = list;
        ListNode cur = list.next;
        prev.next = null;
        
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;        
    }
}


/**
	Note:
	
	Reverse the lists before adding them.
	
**/
