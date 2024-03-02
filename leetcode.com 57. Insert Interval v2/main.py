from typing import List

class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        intervals.append(newInterval)
        intervals.sort(key=lambda x: x[0])

        answer = [intervals.pop(0)]
        while len(intervals) > 0:
            a_start, a_end = answer.pop()
            b_start, b_end = intervals.pop(0)
            over_lapped = False

            if a_start <= b_start and b_end <= a_end:
                over_lapped = True
            elif b_start <= a_start and a_end <= b_end:
                over_lapped = True
            elif a_start <= b_start <= a_end:
                over_lapped = True
            elif a_start <= b_end <= a_end:
                over_lapped = True

            if over_lapped:
                start = min(a_start, b_start)
                end = max(a_end, b_end)
                answer.append([start, end])
            else:
                answer.append([a_start, a_end])
                answer.append([b_start, b_end])

        return answer