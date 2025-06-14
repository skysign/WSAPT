import sys
from typing import List


class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        if len(nums) < 2:
            if target <= nums[0]:
                return 1
            return 0

        answer = sys.maxsize
        left, right = 0, 0
        sm = nums[0]

        while left <= right and right < len(nums):
            if sm >= target:
                answer = min(answer, right + 1 - left)
                sm -= nums[left]
                left += 1
            else: # sm < target
                right += 1
                if right >= len(nums):
                    break
                sm += nums[right]

        if answer == sys.maxsize:
            return 0

        return answer

