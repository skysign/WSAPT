from typing import List

class Solution:
    def canJump(self, nums: List[int]) -> bool:
        reachable = nums[0]

        for i in range(1, len(nums) -1):
            if i <= reachable:
                reachable = max(reachable, i + nums[i])
            else:
                return False

        return reachable >= len(nums) -1