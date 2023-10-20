from typing import List

class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        prefixProducts: List[int] = [1] * len(nums)
        suffixProducts: List[int] = [1] * len(nums)
        rlt: List[int] = []

        prefixProducts[0] *= nums[0]

        for idx in range(1, len(nums)):
            prefixProducts[idx] = (prefixProducts[idx -1] * nums[idx])

        suffixProducts[len(nums)-1] *= nums[len(nums)-1]

        for idx in range(len(nums)-2, -1, -1):
            suffixProducts[idx] = (nums[idx] * suffixProducts[idx +1])

        for idx in range(0, len(nums)):
            tmp = 1
            idxPrefix = idx -1
            idxSuffix = idx +1

            if idxPrefix >= 0:
                tmp *= prefixProducts[idxPrefix]
            if idxSuffix < len(nums):
                tmp *= suffixProducts[idxSuffix]

            rlt.append(tmp)

        return rlt