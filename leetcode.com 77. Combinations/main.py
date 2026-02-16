from typing import List


class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        dt = [v for v in range(1, n + 1)]
        rlt = []

        def mycomb(idx_begin: int, depth: int, path: List, rlt: List[List[int]]):
            if len(path) == depth:
                rlt.append(path)
                return

            for idx in range(idx_begin, len(dt)):
                mycomb(idx + 1, depth, path + [dt[idx]], rlt)

        mycomb(0, k, [], rlt)
        return rlt
