from typing import List

class Solution:
    def trap(self, heights: List[int]) -> int:
        rlt = 0

        n = len(heights)

        heights_lt_max = 0
        heights_lt = [0] * n

        heights_rt_max = 0
        heights_rt = [0] * n

        for idx in range(0, len(heights)):
            heights_lt_max = max(heights_lt_max, heights[idx])
            heights_lt[idx] = heights_lt_max

        for idx in range(len(heights) -1, -1, -1):
            heights_rt_max = max(heights_rt_max, heights[idx])
            heights_rt[idx] = heights_rt_max

        for pmid in range(1, len(heights) -1):
            height_lt = heights_lt[pmid]
            height_rt = heights_rt[pmid]
            height_min = min(height_lt, height_rt)

            if height_min - heights[pmid] > 0:
                rlt += (height_min - heights[pmid])

        return rlt

    def trap3(self, heights: List[int]) -> int:
        rlt = 0

        for pmid in range(1, len(heights) -1):
            height_lt = max(heights[0:pmid])
            height_rt = max(heights[pmid +1:len(heights)])
            height_min = min(height_lt, height_rt)

            if height_min - heights[pmid] > 0:
                rlt += (height_min - heights[pmid])

        return rlt

    def __init__(self):
        self.rlt = 0

    def trap2(self, heights: List[int]) -> int:
        # for plt in range(len(heights)):
        plt = 0
        while plt < len(heights) -1:
            height_lt = heights[plt]
            if height_lt <= 0:
                plt += 1
                continue

            [found, max_height_rt] = self.find_my_height_pair2(plt, height_lt, heights)

            if found != None and max_height_rt != 0:
                plt = found
            else:
                plt += 1

        return self.rlt

    def find_my_height_pair2(self, plt, height_lt, heights):
        minus_area = 0
        max_height_rt = 0
        prt_max = 0

        if height_lt == heights[plt +1]:
            return [None, 0]

        for prt in range(plt + 1, len(heights)):
            if max_height_rt < heights[prt]:
                max_height_rt = heights[prt]
                prt_max = prt

                if max_height_rt >= height_lt:
                    break

        our_height = min(height_lt, max_height_rt)

        for idx in range(plt + 1, prt_max):
            if heights[idx] < our_height:
                minus_area += heights[idx]

        self.rlt += ((prt_max - plt -1) * our_height)
        self.rlt -= minus_area

        return [prt_max, max_height_rt]


    def trap1(self, heights: List[int]) -> int:
        # for plt in range(len(heights)):
        plt = 0
        while plt < len(heights) -1:
            height_lt = heights[plt]
            if height_lt <= 0:
                plt += 1
                continue

            (found, max_height_rt) = self.find_my_height_pair1(plt, height_lt, heights)
            if found == None:
                (found, max_height_rt) = self.find_my_height_pair1(plt, max_height_rt, heights)

            if found != None:
                plt = found
            else:
                plt += 1

        return self.rlt

    def find_my_height_pair1(self, plt, height_lt, heights):
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