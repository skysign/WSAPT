from typing import List


class Solution:
    def summaryRanges(self, nums: List[int]) -> List[str]:
        if len(nums) == 0:
            return []

        answer = []
        stk = [nums[0]]

        for num in nums[1:]:
            if not stk:
                stk.append(num)
            else:
                if stk[-1] + 1 == num:
                    stk.append(num)
                else:
                    self.make_answer(stk, answer)
                    stk.clear()
                    stk.append(num)

        self.make_answer(stk, answer)

        return answer

    def make_answer(self, stk: List[int], answer: List[str]):
        if len(stk) == 0:
            return

        if len(stk) == 1:
            answer.append(str(stk[-1]))
        else:
            answer.append(str(stk[0]) + '->' + str(stk[-1]))
