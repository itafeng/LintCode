/**

Given a collection of intervals, merge all overlapping intervals.

Example
Given intervals => merged intervals:

[                     [
  [1, 3],               [1, 6],
  [2, 6],      =>       [8, 10],
  [8, 10],              [15, 18]
  [15, 18]            ]
]
Challenge 
O(n log n) time and O(1) extra space.

**/

class Solution {
    /**
     * @param intervals, a collection of intervals
     * @return: A new sorted interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        
        List<Interval> res = new ArrayList<>();
        
        if (intervals == null || intervals.isEmpty()) {
            return res;
        }
        
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        int i = 0;
        while (i < intervals.size()) {
            int start = intervals.get(i).start;
            int end = intervals.get(i).end;
            
            int j = i + 1;
            while (j < intervals.size() && intervals.get(j).start <= end) {
                end = Math.max(end, intervals.get(j).end);
                ++j;
            }
            
            res.add(new Interval(start, end));
            i = j;
        }
        
        return res;
    }
}

/**
	Note:
	
	
	
**/
