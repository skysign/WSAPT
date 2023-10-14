from typing import List

class Solution:
    def __init__(self):
        self.rlt = 0

    def trap(self, heights: List[int]) -> int:
        # for plt in range(len(heights)):
        plt = 0
        while plt < len(heights) -1:
            height_lt = heights[plt]
            if height_lt <= 0:
                plt += 1
                continue

            (found, max_height_rt) = self.find_my_height_pair(plt, height_lt, heights)
            if found == None:
                (found, max_height_rt) = self.find_my_height_pair(plt, max_height_rt, heights)

            if found != None:
                plt = found
            else:
                plt += 1

        return self.rlt

    def find_my_height_pair(self, plt, height_lt, heights):
        minus_area = 0
        max_height_rt = 0

        for prt in range(plt + 1, len(heights)):
            height_rt = heights[prt]

            if height_rt <= 0:
                continue

            if height_rt >= height_lt:
                self.rlt += ((prt - plt -1) * min(height_lt, height_rt))
                self.rlt -= minus_area
                return (prt, max_height_rt)
            else:
                minus_area += height_rt
                max_height_rt = max(max_height_rt, height_rt)

        return (None, max_height_rt)