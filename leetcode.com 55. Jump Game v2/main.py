from typing import List


class Solution:
    def canJump(self, nums: List[int]) -> bool:
        can_jump = nums[0]

        for i in range(len(nums)):
            if i <= can_jump:
                can_jump = max(can_jump, i + nums[i])
            else:
                return False

        return len(nums) -1 <= can_jump
