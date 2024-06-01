from typing import List


class Solution:
    def rob(self, nums: List[int]) -> int:
        if len(nums) <= 2:
            return max(nums)

        dp0 = [0 for _ in range(len(nums))]
        dp1 = [0 for _ in range(len(nums))]
        dp2 = [0 for _ in range(len(nums))]

        dp0[0] = nums[0]
        dp0[1] = 0
        dp0[2] = max(dp0[2 - 2], nums[2])

        dp1[0] = 0
        dp1[1] = nums[1]
        dp1[2] = 0

        dp2[0] = 0
        dp2[1] = 0
        dp2[2] = nums[2]

        if len(nums) == 3:
            return max(dp0 + dp1)

        dp0[2] = dp0[0] + nums[2]

        for i in range(3, len(nums) - 1):
            dp0[i] = max(dp0[i - 2], dp0[i - 3]) + nums[i]
            dp1[i] = max(dp1[i - 2], dp1[i - 3]) + nums[i]
            dp2[i] = max(dp2[i - 2], dp2[i - 3]) + nums[i]

        i = len(nums) - 1
        dp1[i] = max(dp1[i - 2], dp1[i - 3]) + nums[i]
        dp2[i] = max(dp2[i - 2], dp2[i - 3]) + nums[i]

        return max(max(dp0), max(dp1), max(dp2))
