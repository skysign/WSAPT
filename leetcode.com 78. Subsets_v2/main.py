from typing import List


def mycombination(nums: List[int], length: int, answer: List):
    def rec(nums: List[int], idx_begin: int, length: int, pathes: List, answer: List):
        if length == 0:
            answer.append(pathes)
            return

        for idx in range(idx_begin, len(nums)):
            n = nums[idx]
            rec(nums, idx + 1, length - 1, pathes + [n], answer)

        return answer

    rec(nums, 0, length, [], answer)

    return answer


class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        answer = []

        for n in range(len(nums) + 1):
            local_answer = []
            mycombination(nums, n, local_answer)
            answer += local_answer

        return answer
