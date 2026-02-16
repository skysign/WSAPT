from typing import List


class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        rlt = []
        candidates.sort()

        def mycomb(idx_begin, path):
            sm = sum(path)

            if sm > target:
                return

            if sm == target:
                rlt.append(path)
                return

            for idx in range(idx_begin, len(candidates)):
                mycomb(idx, path + [candidates[idx]])

        mycomb(0, [])

        return rlt
