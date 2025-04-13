from typing import List


class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        stk = []

        for t in tokens:
            if t in ['+', '-', '*', '/']:
                v2, v1 = stk.pop(), stk.pop()

                if t == '+':
                    v = v1 + v2
                elif t == '-':
                    v = v1 - v2
                elif t == '*':
                    v = v1 * v2
                else: # t == '/':
                    v = v1 / v2
                    v = int(v)

                stk.append(v)
            else:
                stk.append(int(t))

        return stk[0]
