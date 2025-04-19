from typing import List


class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if len(nums) == 0:
            return [-1, -1]

        idx = self.binary_search(nums, target)

        if idx == -1:
            return [-1, -1]

        return self.two_pointer(nums, target, idx)

    def binary_search(self, nums: List[int], target: int) -> int:
        left, right = 0, len(nums) - 1

        while left <= right:
            mid = (left + right) // 2

            if target == nums[mid]:
                return mid

            if target < nums[mid]:
                right = mid - 1
            else:  # nums[mid] < target
                left = mid + 1

        return -1

    def two_pointer(self, nums: List[int], target, bgn: int) -> List[int]:
        left, right = bgn, bgn

        while 0 <= left and nums[left] == target:
            left -= 1

        left += 1

        while right <= len(nums) - 1 and nums[right] == target:
            right += 1

        right -= 1

        return [left, right]
