from typing import List
from collections import defaultdict


class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        dict = defaultdict(int)
        half = len(nums) // 2 + len(nums) % 2

        for n in nums:
            dict[n] += 1

        for key in dict.keys():
            if dict[key] >= half:
                return key

        return 0