from typing import List

class Solution:
    def maxArea(self, heights: List[int]) -> int:
        plt = 0
        prt = len(heights) -1
        max_area = 0

        while plt < prt:
            max_area = max(max_area, (prt -plt) * min(heights[plt], heights[prt]))

            if heights[plt] < heights[prt]:
                plt += 1
            else:
                prt -= 1

        return max_area