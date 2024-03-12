from typing import List


class Solution:
    def decodeString(self, s: str) -> str:
        stk_left: List = []

        for c in s:
            if c == ']':
                candidates: str = ''

                while stk_left and stk_left[len(stk_left) - 1] != '[':
                    candidates = stk_left.pop() + candidates

                value = candidates
                candidates: str = ''
                stk_left.pop()  # '[' 삭제 하기

                number: int = 0

                while stk_left and '0' <= stk_left[len(stk_left) - 1] <= '9':
                    candidates = stk_left.pop() + candidates

                number = int(candidates)
                result = value * number

                stk_left = stk_left + list(result)
            else:
                stk_left.append(c)

        return ''.join(stk_left)
