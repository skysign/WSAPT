class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        dp = [ [0 for _ in range(n +1)] for _ in range(m +1)]

        dp[1][1] = 1

        for idx_row in range(1, m +1):
            for idx_col in range(1, n+1):
                dp[idx_row][idx_col] += (dp[idx_row -1][idx_col] + dp[idx_row][idx_col -1])


        return dp[m][n]