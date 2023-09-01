from typing import List
import copy

class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        output = []

        self.rec(target, 0, 0, [], candidates, output)

        return output

    def rec(self, target, idx_start, local_target, path: List[int], candidates, output: List[List[int]]):
        for idx_candi in range(idx_start, len(candidates)):
            candi = candidates[idx_candi]
            tmp_target = local_target + candi
            local_path = copy.deepcopy(path)

            if tmp_target == target:
                local_path.append(candi)
                output.append(local_path)
                continue
            elif tmp_target > target:
                continue
            elif tmp_target < target:
                local_path.append(candi)
                self.rec(target, idx_candi, tmp_target, local_path, candidates, output)

    def combinationSum_DP(self, candidates: List[int], target: int) -> List[List[int]]:
        tmp = []
        tmp.extend([0])
        tmp.extend(candidates)
        candidates = tmp

        dp = [[[] for _ in range(target +1)] for _ in range(len(candidates))  ]

        for idx_candi in range(1, len(candidates)):
            for local_target in range(1, target +1):
                candi = candidates[idx_candi]

                if local_target - candi > 0:
                    for local_idx_candi in range(1, idx_candi):
                        if len(dp[local_idx_candi][local_target - candi]) > 0:
                            dp[idx_candi][local_target].extend(copy.deepcopy(dp[local_idx_candi][local_target - candi]))

                            for tmp in dp[idx_candi][local_target]:
                                tmp.append(candi)

                if local_target - candi > 0:
                    if len(dp[idx_candi][local_target - candi]) > 0:
                        dp[idx_candi][local_target].extend(copy.deepcopy(dp[idx_candi][local_target - candi]))

                        for tmp in dp[idx_candi][local_target]:
                            tmp.append(candi)

                if local_target - candi == 0:
                    dp[idx_candi][local_target] = copy.deepcopy(dp[idx_candi][local_target - candi])
                    dp[idx_candi][local_target].append([candi])

        answer = []

        for idx_candi in range(1, len(candidates)):
            if len(dp[idx_candi][target]) > 0:
                for tmp in dp[idx_candi][target]:
                    answer.append(tmp)

        return answer

