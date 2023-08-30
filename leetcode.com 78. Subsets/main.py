from typing import List

class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        output = [[]]
        if len(nums) == 1:
            output.append(nums)
            return output

        visited = [False for _ in range(len(nums))]

        for depth in range(2, len(nums) +1):
            self.combination(depth, 0, nums, visited, [], output)

        return output

    def combination(self, depth, idx_start, nums, visited: List[bool], prev_dt: List[int], output):
        depth -= 1

        for idx in range(idx_start, len(nums)):
            local_dt = prev_dt.copy()
            local_dt.append(nums[idx])

            if not visited[idx]:
                visited[idx] = True
                output.append([nums[idx]])

            if 0 == depth:
                output.append(local_dt)
            else:
                self.combination(depth, idx +1, nums, visited, local_dt, output)