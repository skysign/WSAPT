from typing import List


class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        s = ' ' + s
        t = ' ' + t
        l_s = len(s)
        l_t = len(t)
        dp: List[List[int]] = [[[-1, 0] for _ in range(len(s))] for _ in range(len(t))]
        dp[0][0][0] = 0  # s의 0~c인덱스까지, t의 0~r인덱스까지 비교하여, match된 distinct subsequence 임을 뜻함
        dp[0][0][1] = 1  # s의 0~c인덱스까지, t의 0~r인덱스까지 비교하여, match된 distinct subsequence 의 개수

        answer = 0

        for r in range(1, l_t):
            for c in range(r, l_s - (l_t - r - 1)):
                if t[r] == s[c]:
                    for b in range(r - 1, c):
                        if dp[r - 1][b][0] == r - 1:
                            dp[r][c][0] = r
                            dp[r][c][1] += dp[r - 1][b][1]

                            if dp[r][c][0] == l_t - 1:
                                answer += dp[r - 1][b][1]

        return answer
