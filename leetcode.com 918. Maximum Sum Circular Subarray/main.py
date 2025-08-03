from typing import List


class Solution:
    def maxSubarraySumCircular(self, nums: List[int]) -> int:
        localMax, localMin = nums[0], nums[0]
        mx, mn, sm = nums[0], nums[0], nums[0]

        for n in nums[1:]:
            localMax = max(localMax + n, n)
            localMin = min(localMin + n, n)
            mx = max(mx, localMax)
            mn = min(mn, localMin)
            sm += n

        if mx > 0:
            return max(mx, sm - mn)

        return mx
