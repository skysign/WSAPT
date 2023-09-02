import copy
from typing import List

class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        answer = [[]]
        visited = {}

        nums.sort()

        self.rec(0, 0, nums, [], visited, answer)

        return answer

    def rec(self, depth, idx_start, nums, path, visited, answer):
        for idx in range(idx_start, len(nums)):
            num = nums[idx]

            if visited.keys().__contains__((depth, num)):
                continue
            else:
                visited[(depth, num)] = {}

            path.append(num)
            answer.append(copy.deepcopy(path))

            self.rec(depth +1, idx +1, nums, path, visited[(depth, num)], answer)

            path.pop(len(path) -1)