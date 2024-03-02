from typing import List

class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        if len(intervals) == 0:
            return [newInterval]

        tmp = []
        start_new, end_new = newInterval
        START_DEFAULT = 10 ** 5 + 1
        END_DEFAULT = -1
        start_min, end_max = START_DEFAULT, END_DEFAULT
        over_warped_at_least_once = False

        for idx in range(len(intervals)):
            itv = intervals[idx]
            start, end = itv
            over_lapped = False

            # start_new, start, end end_new
            # start, start_new end
            # start, end_new end
            if start_new <= start and end <= end_new:
                over_lapped = True
                over_warped_at_least_once = True
            elif start <= start_new <= end:
                over_lapped = True
                over_warped_at_least_once = True
            elif start <= end_new <= end:
                over_lapped = True
                over_warped_at_least_once = True

            if over_lapped:
                start_min = min(start_min, start_new)
                end_max = max(end_max, end_new)
                start_min = min(start_min, start)
                end_max = max(end_max, end)
            else:
                if start_min != START_DEFAULT:
                    tmp.append([start_min, end_max])
                    start_min, end_max = START_DEFAULT, END_DEFAULT

                tmp.append(itv)

        if start_min != START_DEFAULT:
            tmp.append([start_min, end_max])

        if over_warped_at_least_once == False:
            intervals.append(newInterval)
            intervals.sort(key=lambda x: x[0])
            return intervals

        return tmp