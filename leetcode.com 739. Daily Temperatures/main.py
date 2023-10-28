from typing import List


class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        rlt = [0 for x in range(len(temperatures))]
        stk = []

        for i in range(len(temperatures)):
            temper = temperatures[i]

            # stk 이 비어 있을 때에도 whie loop에 들어 오지 안도록, stk and를 해줍니다.
            # i가 가리키는 현재 온도(temper) 보다,
            # 낮은 온도가 stk에 저장되어 있으면
            # 하나씩 꺼내서, 날짜를 계산해, rlt에 저장합니다.
            while stk and stk[-1][1] < temper:
                index, _ = stk.pop()
                rlt[index] = i - index

            # i, temper도 나보다 높은 온도가 나올 때, 계산될 수 있도록, stk에 저장합니다.
            stk.append([i, temper])

        return rlt