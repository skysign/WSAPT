from typing import List


def solution(n, tops):
    dp: List[List[int]] = [[0, 0] for _ in range(n)]
    # 0이 / 마름모, 삼각형, 즉, 1번이 아닌 모든 경우의 수
    # 1이 \ 마름모
    MOD = 10007

    if tops[0] == 1:
        dp[0][0] = 3
        dp[0][1] = 1
    else:
        dp[0][0] = 2
        dp[0][1] = 1

    for idx in range(1, n):
        if tops[idx] == 1:
            dp[idx][0] = dp[idx - 1][0] * 3 + dp[idx - 1][1] * 2
        else:
            dp[idx][0] = dp[idx - 1][0] * 2 + dp[idx - 1][1]
        dp[idx][1] = dp[idx - 1][0] + dp[idx - 1][1]

        dp[idx][0] %= MOD
        dp[idx][1] %= MOD

    answer = (dp[n - 1][0] + dp[n - 1][1]) % MOD

    return answer
