from typing import List

class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        dt = {}

        for n in nums:
            if n not in dt.keys():
                dt[n] = 1
            else:
                del dt[n]

        return list(dt.keys())[0]