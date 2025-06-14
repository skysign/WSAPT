from typing import List


class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        answer = []
        intervals.append(newInterval)
        intervals.sort()
        ps, pe = intervals[0]

        for s, e in intervals[1:]:
            if ps <= s <= pe or ps <= e <= pe:
                ps, pe = min(ps, s), max(pe, e)
            else:
                answer.append([ps, pe])
                ps, pe = s, e

        answer.append([ps, pe])
        return answer
