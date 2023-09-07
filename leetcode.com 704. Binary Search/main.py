from typing import List

def binarySearch(nums, idx_bgn, idx_end, target) -> int:
    if idx_bgn > idx_end:
        return -1

    idx_mid = int((idx_bgn + idx_end) / 2)

    if nums[idx_mid] == target:
        return idx_mid
    elif nums[idx_mid] < target:
        idx_bgn = idx_mid +1
    else:
        idx_end = idx_mid -1

    return binarySearch(nums, idx_bgn, idx_end, target)

class Solution:
    def search(self, nums: List[int], target: int) -> int:
        return binarySearch(nums, 0, len(nums)-1, target)


