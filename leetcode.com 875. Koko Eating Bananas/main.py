import math
from typing import List

class Solution:
    def minEatingSpeed(self, piles: List[int], hours: int) -> int:
        return self.binary_search(1, max(piles), piles, hours)

    def binary_search(self, k_lt, k_rt, piles, hours):
        while k_lt < k_rt:
            mid = int((k_lt + k_rt) / 2)
            mid_hours = self.get_hours(piles, mid)

            if mid_hours > hours:
                k_lt = mid + 1

            else: # k_mid_hours <= target
                k_rt = mid

        return k_lt


    def get_hours(self, piles, k):
        return sum(math.ceil(pile / k) for pile in piles)