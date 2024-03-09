from typing import List

class Solution:
    def jump(self, nums: List[int]) -> int:
        length = len(nums)
        depth = 0
        reachable = 0
        max_reachable_in_current_depth = 0  # 현재 depth에서 가장 멀리 갈 수 있는 거리, 이값을 넘기면 depth 를 +1 증가해야 함

        for i in range(0, length -1):
            reachable = max(reachable, i + nums[i])

            if reachable >= length - 1:
                depth += 1
                break

            if max_reachable_in_current_depth == i:
                max_reachable_in_current_depth = reachable
                depth += 1

        return depth
