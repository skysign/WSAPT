from typing import List


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        nums = [1] + nums + [1]
        answer = [1 for _ in range(len(nums))]
        prefix = [1 for _ in range(len(nums))]
        suffix = [1 for _ in range(len(nums))]

        for idx in range(1, len(nums) - 1):
            prefix[idx] = prefix[idx - 1] * nums[idx]

        for idx in range(len(nums) - 2, 0, -1):
            suffix[idx] = suffix[idx + 1] * nums[idx]

        for idx in range(1, len(nums) - 1):
            answer[idx] = prefix[idx - 1] * suffix[idx + 1]

        return answer[1:-1]
