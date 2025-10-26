from typing import List
from collections import defaultdict


class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        dict = defaultdict(int)

        for n in nums:
            dict[n] += 1

        for k in dict.keys():
            if dict[k] == 1:
                return k

        return 0
