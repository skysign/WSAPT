from typing import List


def mycombination(nums: List[int], length: int, answer: List):
    def rec(nums: List[int], idx_begin: int, length: int, pathes: List, answer: List):
        if length == 0:
            if pathes not in answer:
                answer.append(pathes)
            return

        for idx in range(idx_begin, len(nums)):
            n = nums[idx]
            rec(nums, idx + 1, length - 1, pathes + [n], answer)

        return answer

    rec(nums, 0, length, [], answer)

    return answer


class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        answer = []
        nums.sort()

        for length in range(len(nums) + 1):
            answer_local = []
            mycombination(nums, length, answer_local)
            answer += answer_local

        return answer
