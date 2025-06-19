from typing import List


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        answer = []
        visited = [False for _ in range(len(nums))]
        self.permutation(0, nums, visited, [], 0, answer)
        return answer

    def permutation(self, bgn, nums, visited, building: List, depth, answer):
        if depth == len(nums):
            answer.append(building)
            return

        for idx in range(len(nums)):
            if visited[idx]:
                continue

            visited[idx] = True
            self.permutation(0, nums, visited, building + [nums[idx]], depth + 1, answer)
            visited[idx] = False
