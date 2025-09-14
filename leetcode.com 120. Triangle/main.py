from typing import List


class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        rows = len(triangle)
        cols = len(triangle[-1])

        dp = [[10 ** 4 * 200 + 1 for _ in range(cols)] for _ in range(rows)]
        dp[0][0] = triangle[0][0]

        for r in range(1, rows):
            for c in range(0, r + 1):
                dp[r][c] = min(dp[r][c], dp[r - 1][c] + triangle[r][c])

                if c > 0:
                    dp[r][c] = min(dp[r][c], dp[r - 1][c - 1] + triangle[r][c])

        return min(dp[-1])
