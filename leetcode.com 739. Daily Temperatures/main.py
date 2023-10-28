from typing import List


class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        rlt = [0 for x in range(len(temperatures))]
        stk = []

        for i in range(len(temperatures)):
            temper = temperatures[i]

            while stk and stk[-1][1] < temper:
                index, _ = stk.pop()
                rlt[index] = i - index

            stk.append([i, temper])

        return rlt