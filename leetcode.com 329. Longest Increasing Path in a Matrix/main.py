from typing import List

class Solution:
    def longestIncreasingPath(self, dt: List[List[int]]) -> int:
        dp = [[-1 for _ in range(len(dt[0]))] for _ in range(len(dt))]

        for sy in range(len(dt)):
            for sx in range(len(dt[0])):
                self.dfs(sy, sx, 1, dp, dt)

        answer = 0

        for sy in range(len(dt)):
            for sx in range(len(dt[0])):
                answer = max(answer, dp[sy][sx])

        return answer

    def dfs(self, sy, sx, depth, dp, dt):
        if dp[sy][sx] >= depth:
            return

        dp[sy][sx] = depth

        dyxs = [(-1, 0), (0, 1), (1, 0), (0, -1)]
        for dyx in dyxs:
            dy, dx = dyx
            ty = sy + dy
            tx = sx + dx

            if self.is_in(ty, tx, dt):
                if dt[sy][sx] < dt[ty][tx]:
                    if dp[ty][tx] < depth +1:
                        self.dfs(ty, tx, depth +1, dp, dt)


    def is_in(self, idx_row, idx_col, map):
        if (0 <= idx_row < len(map)) and (0 <= idx_col < len(map[0])):
            return True

        return False