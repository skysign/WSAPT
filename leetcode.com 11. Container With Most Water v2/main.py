from typing import List


class Solution:
    def maxArea(self, height: List[int]) -> int:
        idx_left, idx_right = 0, len(height) - 1
        mx = 0

        while idx_left < idx_right:
            mx = max(mx, (idx_right - idx_left) * min(height[idx_left], height[idx_right]))

            if height[idx_left] <= height[idx_right]:
                idx_left += 1
            else:
                idx_right -= 1

        return mx