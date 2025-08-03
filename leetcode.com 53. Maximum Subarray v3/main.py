from typing import List


class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        localmx, mx = nums[0], nums[0]

        for n in nums[1:]:
            localmx = max(localmx + n, n)
            mx = max(mx, localmx)

        return mx