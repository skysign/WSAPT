from typing import List
from collections import defaultdict

class Solution:
    def __init__(self, nums: List[int]):
        self.picks = defaultdict(list)

        for i in range(len(nums)):
            self.picks[nums[i]].append(i)

    def pick(self, target: int) -> int:
        rtn = self.picks[target].pop(0)
        self.picks[target].append(rtn)
        return rtn
