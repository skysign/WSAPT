from typing import List

class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        answer: List = []
        nums.sort()
        visited: List[bool] = [False for _ in range(len(nums))]

        def rec(nums, depth_crnt, depth_mx, pathes, answer: List):
            if depth_crnt == depth_mx:
                answer.append(pathes)
                return

            for idx in range(len(nums)):
                if not visited[idx]:
                    visited[idx] = True
                    rec(nums, depth_crnt +1, depth_mx, pathes + [nums[idx]], answer)
                    visited[idx] = False

        rec(nums, 0, len(nums), [], answer)

        return answer