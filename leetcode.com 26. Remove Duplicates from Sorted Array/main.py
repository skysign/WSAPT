from typing import List


class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        k = len(nums)
        count_duplicate = 0

        for i in range(len(nums)-1, 0, -1):
            if nums[i] == nums[i-1]:
                nums.pop(i)
                count_duplicate += 1

        return k - count_duplicate
