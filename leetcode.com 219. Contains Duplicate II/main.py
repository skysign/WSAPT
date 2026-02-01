from typing import List


class Solution:
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        sliding_window = set()

        for idx in range(len(nums)):
            if idx > k:
                sliding_window.remove(nums[idx - k - 1])

            if nums[idx] in sliding_window:
                return True

            sliding_window.add(nums[idx])

        return False
