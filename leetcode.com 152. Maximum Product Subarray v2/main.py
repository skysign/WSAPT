from typing import List


class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        mx = nums[0]
        mn_prev = nums[0]
        mx_prev = nums[0]

        for v in nums[1:]:
            tmp_mn_prev = mn_prev
            tmp_mx_prev = mx_prev
            mn_prev = min(v, tmp_mn_prev * v, tmp_mx_prev * v)
            mx_prev = max(v, tmp_mn_prev * v, tmp_mx_prev * v)
            mx = max(mx, mx_prev)

        return mx
