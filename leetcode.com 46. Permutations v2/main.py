import copy
from typing import List


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        answer = []
        self.my_permute(0, len(nums), [False for _ in range(len(nums))], nums, 0, [], answer)
        return answer


    def my_permute(self, idx_bgn, length, visited: List[bool], nums: List[int], depth: int, path: List[int], answer: List[List[int]]):
        if depth == length:
            answer.append(copy.deepcopy(path))
            return

        for idx in range(idx_bgn, length):
            if not visited[idx]:
                visited[idx] = True
                self.my_permute(idx_bgn, length, visited, nums, depth +1, path + [nums[idx]], answer)
                visited[idx] = False
