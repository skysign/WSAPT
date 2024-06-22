from typing import List


class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        row: int = len(text1) + 1
        col: int = len(text2) + 1
        dp: List[List[int]] = [[0 for _ in range(col)] for _ in range(row)]

        for r in range(1, row):
            i1 = r - 1
            for c in range(1, col):
                i2 = c - 1

                if text1[i1] == text2[i2]:
                    dp[r][c] = dp[r - 1][c - 1] + 1
                else:
                    dp[r][c] = max(dp[r - 1][c], dp[r][c - 1])

        return dp[row - 1][col - 1]