from typing import List

class Solution:
    def twoSum(self, dt: List[int], target: int) -> List[int]:
        sum = -2001

        pl = 0
        pr = len(dt) -1

        while pl < pr:
            sum = dt[pl] + dt[pr]

            if sum == target:
                return [pl +1, pr +1]
            else:
                if sum > target:
                    pr -= 1
                if sum < target:
                    pl += 1

        return []