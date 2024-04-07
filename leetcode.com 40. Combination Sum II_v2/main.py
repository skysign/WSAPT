from collections import defaultdict
from typing import List


class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        output: List = []
        visitied: dict = defaultdict(lambda: False)
        candidates.sort()
        self.rec(candidates, 0, target, [], 0, output, visitied)

        return output

    def rec(self, candidates: List, idx_begin: int, target, pathes: List, sm: int, output: List, visitied: dict):
        if target == sm:
            output.append(pathes)
            return

        for idx in range(idx_begin, len(candidates)):
            candi = candidates[idx]

            if sm + candi <= target:
                if visitied[tuple(pathes + [candi])]:
                    continue
                else:
                    visitied[tuple(pathes + [candi])] = True

                self.rec(candidates, idx + 1, target, pathes + [candi], sm + candi, output, visitied)
            else:
                break
