from typing import List


class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        answer = len(points)
        points.sort(key=lambda x: x[0])
        prev = points[0]

        for idx in range(1, len(points)):
            crnt = points[idx]

            if crnt[0] <= prev[1]:
                answer -= 1
                prev = [crnt[0], min(prev[1], crnt[1])]
            else:
                prev = crnt

        return answer
