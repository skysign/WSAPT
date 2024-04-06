from typing import List

class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        output = []
        candidates.sort()
        self.rec(candidates, 0, target, [], 0, output)

        return output

    def rec(self, candidates: List, idx_begin, target, pathes: List, sm, output: List):
        if target == sm:
            output.append(pathes)
            return

        for idx in range(idx_begin, len(candidates)):
            candi = candidates[idx]
            if candi <= target - sm:
                self.rec(candidates, idx, target, pathes + [candi], sm + candi, output)
            else: # early stop
                break