from typing import List

#(())(()) n = 4일 때 답 중에 1개임

class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        rlt = self.gen(n, '', '')
        answer = list(set(rlt))
        answer.sort()
        return answer

    def gen(self, n, lt: str, rt: str) -> List[str]:
        if n == 0:
            return [lt + rt]

        r0 = self.gen(n - 1, lt + '(', ')' + rt)
        r1 = self.gen(n - 1, '(' + lt, rt + ')')
        r2 = self.gen(n - 1, lt + '()' + rt, '')
        r3 = self.gen(n - 1, lt + '()', rt)
        r4 = self.gen(n - 1, '', lt + '()' + rt)
        r5 = self.gen(n - 1, lt, '()' + rt)

        r = r0 + r1 + r2 + r3 + r4 + r5

        return r