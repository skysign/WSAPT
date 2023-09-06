import copy
from typing import List

class Solution:
    def letterCasePermutation(self, s: str) -> List[str]:
        output, answer = [], []
        dt: List[str] = [ch for ch in s]

        self.rec(0, dt, output)

        for out in output:
            ans = ''
            for ch in out:
                ans += ch

            answer.append(ans)

        return answer

    def rec(self, idx_bgn, dt: List[str], answer):
        answer.append(copy.deepcopy(dt))

        if idx_bgn >= len(dt):
            return

        for idx in range(idx_bgn, len(dt)):
            if not dt[idx].isalpha():
                continue

            local_dt = copy.deepcopy(dt)
            local_dt[idx] = local_dt[idx].swapcase()
            self.rec(idx +1, local_dt, answer)