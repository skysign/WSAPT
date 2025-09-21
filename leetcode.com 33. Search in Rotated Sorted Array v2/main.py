from typing import List

# 유튜브 문제 풀이: https://youtu.be/hAOMTq6XQj4?si=nCVWEwu3TvhyWfN8

class Solution:
    def search(self, nums: List[int], target: int) -> int:
        idx_lt, idx_rt = 0, len(nums) - 1

        while idx_lt <= idx_rt:
            idx_mid = (idx_lt + idx_rt) // 2

            if nums[idx_mid] == target:
                return idx_mid

            if nums[idx_lt] <= target <= nums[idx_mid]:
                idx_rt = idx_mid - 1
            elif nums[idx_lt] <= nums[idx_mid] < target:
                idx_lt = idx_mid + 1
            elif target < nums[idx_lt] <= nums[idx_mid]:
                idx_lt = idx_mid + 1
            elif nums[idx_mid] <= target <= nums[idx_rt]:
                idx_lt = idx_mid + 1
            elif target < nums[idx_mid] <= nums[idx_rt]:
                idx_rt = idx_mid - 1
            elif target > nums[idx_rt] >= nums[idx_mid]:
                idx_rt = idx_mid - 1

        return -1
