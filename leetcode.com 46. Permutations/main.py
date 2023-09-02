import copy
from typing import List

class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        answer = []
        visited = [False for _ in range(len(nums))]
        self.permutation(nums, len(nums), answer)

        return answer

    def permutation(self, nums: List[int], length: int, answer: List[List[int]]):
        nums.sort()
        visited = [False for _ in range(len(nums))]

        def rec(path: List[int], visited: List[int]):
            if len(path) == length:
                answer.append(copy.deepcopy(path))
                return

            for idx in range(len(nums)):
                if not visited[idx]:
                    num = nums[idx]
                    path.append(num)
                    visited[idx] = True
                    rec(path, visited)
                    visited[idx] = False
                    path.pop(len(path) -1)


        rec([], visited)
