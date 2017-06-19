/**

Given a non-overlapping interval list which is sorted by start point.

Insert a new interval into it, make sure the list is still in order and non-overlapping (merge intervals if necessary).

Example
Insert [2, 5] into [[1,2], [5,9]], we get [[1,9]].

Insert [3, 4] into [[1,2], [5,9]], we get [[1,2], [3,4], [5,9]].

**/

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * Insert newInterval into intervals.
     * @param intervals: Sorted interval list.
     * @param newInterval: A new interval.
     * @return: A new sorted interval list.
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        boolean inserted = false;
        
        // write your code here
        for (int i = 0; i < intervals.size(); ++i) {
            if (intervals.get(i).end < newInterval.start) {
                result.add(intervals.get(i));
                continue;
            }
            
            if (intervals.get(i).start <= newInterval.end) {
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            }            
            else {
                if (!inserted) {
                    result.add(newInterval);
                    inserted = true;
                }
                result.add(intervals.get(i));
            }
        }
        
        if (!inserted) {
            result.add(newInterval);
        }

        return result;
    }
}

/**
	Note:
	
	
	
**/
