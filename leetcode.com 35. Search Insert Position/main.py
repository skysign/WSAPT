from typing import List


class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        if target < nums[0]:
            return 0

        if target > nums[-1]:
            return len(nums)

        nums = [-10001] + nums + [10001]
        idx_left, idx_right = 1, len(nums) - 2
        answer = 0

        while idx_left <= idx_right:
            idx_mid = (idx_left + idx_right) // 2

            if target == nums[idx_mid]:
                answer = idx_mid
                break

            if nums[idx_mid - 1] < target < nums[idx_mid]:
                answer = idx_mid
                break

            if nums[idx_mid] < target < nums[idx_mid] + 1:
                answer = idx_mid + 1
                break

            if nums[idx_mid] < target:
                idx_left = idx_mid + 1
            else:   # target < nums[idx_mid]
                idx_right = idx_mid - 1

        return answer - 1
