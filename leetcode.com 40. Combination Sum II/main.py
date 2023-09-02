from typing import List
import copy

class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        output = []
        # depth, candi 로 된 트리가 필요함
        # dpeth, candi -> children

        # key depth, parent_candi
        root_visited = {}
        candidates.sort()
        self.rec(0, root_visited, -1, 0, target, 0, [], candidates, output)

        return output

    def rec(self, depth, visited, parent_candi, idx_start: int, target: int, local_target: int, path: List[int], candidates: List[int], output):
        for idx_candi in range(idx_start, len(candidates)):
            candi = candidates[idx_candi]

            if visited.__contains__((depth, candi)):
                continue
            else:
                visited[(depth, candi)] = {}

            tmp_target = local_target + candi

            if tmp_target == target:
                tmp_path = copy.deepcopy(path)
                tmp_path.append(candi)
                output.append(tmp_path)
                continue
            elif tmp_target > target:
                continue
            else:   # tmp_target < target
                path.append(candi)
                self.rec(depth +1, visited[(depth, candi)], candi, idx_candi +1, target, tmp_target, path, candidates, output)
                path.pop(len(path) -1)