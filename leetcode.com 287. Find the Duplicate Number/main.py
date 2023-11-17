from typing import List
from collections import defaultdict


class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        dict = defaultdict(int)

        for num in nums:
            if dict[num] == 0:
                dict[num] += 1
            else:
                return num

        return None