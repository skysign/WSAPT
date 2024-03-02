from functools import cmp_to_key
from typing import List

class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        intervals.sort(key=lambda x: x[1])

        count_total = len(intervals)
        count_non_overlapped = 1
        prev = 0

        for idx in range(1, count_total):
            # prev의 end가 idx의 start 보다 작거나 같으면
            # 이 둘은 overlapped 되는 부분이 없다는 의미
            if intervals[prev][1] <= intervals[idx][0]:
                prev = idx
                count_non_overlapped += 1

        return count_total - count_non_overlapped