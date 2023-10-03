from typing import List

NOT_FOUND = -1 * 4 ** 10 -1

def bs(dt, idx_lt, idx_rt, target):
    while idx_lt <= idx_rt:
        idx_mid = (idx_lt + idx_rt) // 2

        if dt[idx_mid] == target:
            return idx_mid

        if dt[idx_lt] <= target <= dt[idx_mid]:
            idx_rt = idx_mid -1
        elif dt[idx_lt] <= dt[idx_mid] < target:
            idx_lt = idx_mid +1
        elif target < dt[idx_lt] <= dt[idx_mid]:
            idx_lt = idx_mid +1
        elif dt[idx_mid] <= target <= dt[idx_rt]:
            idx_lt = idx_mid +1
        elif target < dt[idx_mid] <= dt[idx_rt]:
            idx_rt = idx_mid -1
        elif target > dt[idx_rt] >= dt[idx_mid]:
            idx_rt = idx_mid -1

    return NOT_FOUND

class Solution:
    def search(self, nums: List[int], target: int) -> int:
        rlt = bs(nums, 0, len(nums) -1, target)

        if rlt == NOT_FOUND:
            return -1

        return rlt
