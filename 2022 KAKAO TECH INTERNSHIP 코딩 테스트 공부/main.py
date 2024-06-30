import sys
from typing import List


def solution(alp: int, cop: int, problems: List[List[int]]):
    alp_max, cop_max = alp, cop

    for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
        alp_max = max(alp_max, alp_req)
        cop_max = max(cop_max, cop_req)

    dp: List[List[int]] = [[sys.maxsize for _ in range(cop_max + 1)] for _ in range(alp_max + 1)]

    for idx_alp in range(alp + 1):
        for idx_cop in range(cop + 1):
            dp[idx_alp][idx_cop] = 0

    problems = [[0, 0, 1, 0, 1], [0, 0, 0, 1, 1]] + problems

    for idx_alp in range(len(dp)):
        for idx_cop in range(len(dp[0])):
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                # 문제를 풀 수 있는 알고력과 코딩력을 가지고 있으면
                if idx_alp >= alp_req and idx_cop >= cop_req:
                    dst_alp = min(alp_max, idx_alp + alp_rwd)
                    dst_cop = min(cop_max, idx_cop + cop_rwd)

                    dp[dst_alp][dst_cop] = min(dp[dst_alp][dst_cop], dp[idx_alp][idx_cop] + cost)

    return dp[alp_max][cop_max]
