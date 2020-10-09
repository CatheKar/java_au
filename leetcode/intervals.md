# Intervals

+ [Non-overlapping Intervals](#non-overlapping-intervals)
+ [Merge Intervals](#merge-intervals)
+ [Insert Interval](#insert-interval)

## Non-overlapping Intervals

https://leetcode.com/problems/non-overlapping-intervals/

```java
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length < 2) return 0;
        Arrays.sort(intervals, (a, b)->(a[0] - b[0]));
        int count = 0, last_included = 0;
        for(int i = 1; i < intervals.length; ++i){
            if(intervals[i][0] < intervals[last_included][1]){ // Overlap
                count++;
                if(intervals[i][1] < intervals[last_included][1]) last_included = i;
            } else
                last_included = i;
        }
        return count;
    }
```

## Merge Intervals

https://leetcode.com/problems/merge-intervals/

```java
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
			return intervals;

		Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

		List<int[]> result = new ArrayList<>();
		int[] newInterval = intervals[0];
		result.add(newInterval);
		for (int[] interval : intervals) {
			if (interval[0] <= newInterval[1])
				newInterval[1] = Math.max(newInterval[1], interval[1]);
			else {                             
				newInterval = interval;
				result.add(newInterval);
			}
		}

		return result.toArray(new int[result.size()][]);
    }
```

## Insert Interval

https://leetcode.com/problems/insert-interval/

```java
    public int[][] insert(int[][] intervals, int[] newInterval) {
 List<int[]> result = new ArrayList();
 int i = 0, n = intervals.length;
 while(i < n && intervals[i][1] < newInterval[0]) result.add(intervals[i++]);

 int[] mI = newInterval;
 while(i < n && intervals[i][0] <= newInterval[1]){
 mI[0] = Math.min(mI[0], intervals[i][0]);
 mI[1] = Math.max(mI[1], intervals[i++][1]);
        }
 result.add(mI);

 while(i < n) result.add(intervals[i++]);

 return result.toArray(new int[result.size()][2]);
    }
```
