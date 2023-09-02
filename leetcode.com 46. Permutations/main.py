import copy
from typing import List


def permutation(nums: List[int], length: int, path: List[int], answer: List[List[int]]):
    visited = [False for _ in range(len(nums))]

    def rec(path):
        if len(path) == length:
            answer.append(copy.deepcopy(path))
            return

        for idx in range(len(nums)):
            if not visited[idx]:
                visited[idx] = True
                path.append(nums[idx])
                rec(path)
                path.pop(len(path) -1)
                visited[idx] = False

    rec(path)


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        answer = []
        nums.sort()

        permutation(nums, len(nums), [], answer)

        return answer


