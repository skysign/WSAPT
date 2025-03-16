from typing import List


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        myset = set()

        for idx_left in range(0, len(nums) - 2):
            target = -nums[idx_left]
            idx_mid = idx_left + 1
            idx_right = len(nums) - 1

            while idx_mid < idx_right:
                if nums[idx_mid] + nums[idx_right] == target:
                    myset.add((nums[idx_left], nums[idx_mid], nums[idx_right]))
                    idx_mid += 1
                    idx_right -= 1
                elif nums[idx_mid] + nums[idx_right] < target:
                    idx_mid += 1
                elif nums[idx_mid] + nums[idx_right] > target:
                    idx_right -= 1

        return [list(item) for item in list(myset)]