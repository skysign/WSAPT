from typing import List


class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort(key = lambda x: x[0])
        answer = [intervals.pop(0)]

        while len(intervals) > 0:
            a_left, a_right = answer.pop()
            b_left, b_right = intervals.pop(0)

            b_overlapping = False

            # intervals을 정렬했기 때문에 a_left <= b_left 는 항상 성립함
            if (b_right <= a_right)  \
                or (a_left <= b_left <= a_right)    \
                or (a_left <= b_right <= a_right):
                b_overlapping = True

            if b_overlapping:
                l = min(a_left, b_left)
                r = max(a_right, b_right)
                answer.append([l, r])
            else:
                answer.append([a_left, a_right])
                answer.append([b_left, b_right])

        return answer