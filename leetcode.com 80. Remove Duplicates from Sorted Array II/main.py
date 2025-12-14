from typing import List


class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        k = len(nums)

        if k <= 2:
            return k

        idx = k - 3

        while 0 <= idx:
            pprev, prev, target = idx, idx + 1, idx + 2
            delta = 0

            while 0 <= pprev and nums[pprev] == nums[prev] and nums[prev] == nums[target]:
                k -= 1
                idx -= 1
                pprev, prev, target = idx, idx + 1, idx + 2
                delta += 1

            if delta > 0:
                for t in range(prev, len(nums) - delta):
                    nums[t] = nums[t + delta]

            if delta == 0:
                idx -= 1

        return k
