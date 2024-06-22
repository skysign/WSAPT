from typing import List


class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        row = m + 1
        col = n + 1
        dp: List[List[int]] = [[0 for _ in range(col)] for _ in range(row)]

        for r in range(1, row):
            for c in range(1, col):
                if r == 1 and c == 1:
                    dp[1][1] = 1
                else:
                    dp[r][c] = dp[r - 1][c] + dp[r][c - 1]

        return dp[row - 1][col - 1]
