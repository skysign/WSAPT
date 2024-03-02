from typing import List
import heapq

class Solution:
    def minInterval(self, intervals: List[List[int]], queries: List[int]) -> List[int]:
        length = len(intervals)
        intervals.sort()
        heap = []
        dict = {}
        idx = 0

        for q in sorted(queries):
            while idx < length and intervals[idx][0] <= q:
                left, right = intervals[idx]
                l = right - left + 1
                heapq.heappush(heap, [l, right])
                idx += 1

            while heap and heap[0][1] < q:
                heapq.heappop(heap)

            dict[q] = heap[0][0] if heap else -1

        return [dict[q] for q in queries]
