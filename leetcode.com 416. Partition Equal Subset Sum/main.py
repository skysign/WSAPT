import copy

class Solution:
    def canPartition(self, nums: list[int]) -> bool:
        dt = copy.deepcopy(nums)
        dpL2R = [0 for _ in range(len(nums))]
        dpR2L = [0 for _ in range(len(nums))]

        dpL2R[0] = dt[0]
        for idx in range(1, len(nums), 1):
            dpL2R[idx] = dpL2R[idx -1] + dt[idx]

        dpR2L[len(nums) - 1] = dt[len(nums) - 1]
        for idx in range(len(nums)-2, -1, -1):
            dpR2L[idx] = dpR2L[idx + 1] + dt[idx]

        for idx in range(1, len(nums) -1):
            if dpL2R[idx-1] + dpR2L[idx+1] == dt[idx]:
                return True

        return False