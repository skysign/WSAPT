import numpy as np
from pslolviz import *

class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        dp = [[0 for _ in range(n + 1)] for _ in range(m + 1)]
        dp[1][1] = 1

        for idx_row in range(1, m + 1):
            for idx_col in range(1, n + 1):
                dp[idx_row][idx_col] += (dp[idx_row - 1][idx_col] + dp[idx_row][idx_col - 1])

        return dp[m][n]


    def uniquePaths_dump_dp(self, m: int, n: int) -> int:
        # dp = [ [0 for _ in range(n +1)] for _ in range(m +1)]
        dp = np.zeros((m +1, n +1), dtype=int)
        dp[1][1] = 1

        frame_count = 0

        for idx_row in range(1, m +1):
            for idx_col in range(1, n+1):
                dp[idx_row][idx_col] += (dp[idx_row -1][idx_col] + dp[idx_row][idx_col -1])
                self.saveDPtoImgs(dp, frame_count, idx_row, idx_col)
                frame_count += 1

        return dp[m][n]

    def saveDPtoImgs(self, dp, frame_count, idx_row, idx_col):
        g = matrixviz(dp, idx_row, idx_col)
        filename = './dp_' + str(idx_row) + 'x' + str(idx_col)
        filename_dot = filename + '.dot'
        g.render(format='svg', filename=filename_dot)

        filename = './dp_' + str(frame_count)
        filename_dot = filename + '.dot'
        g.render(format='png', filename=filename_dot)