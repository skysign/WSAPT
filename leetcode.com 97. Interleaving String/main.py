from functools import cache # dfs1

class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        if len(s3) != len(s1) + len(s2):
            return False

        dp = [[False] * len(' ' + s2) for _ in range(len(' ' + s1))]
        dp[0][0] = True

        for idx_row in range(1, len(' ' +s1)):
            idx_s1 = idx_row -1
            dp[idx_row][0] = dp[idx_row -1][0] and s1[idx_s1] == s3[idx_s1]

        for idx_col in range(1, len(' ' +s2)):
            idx_s2 = idx_col -1
            dp[0][idx_col] = dp[0][idx_col -1] and s2[idx_s2] == s3[idx_s2]

        for idx_row in range(1, len(' ' +s1)):
            idx_s1 = idx_row -1

            for idx_col in range(1, len(' ' +s2)):
                idx_s2 = idx_col -1

                r1, r2 = False, False

                c1 = s1[idx_s1]
                c2 = s2[idx_s2]
                c3 = s3[idx_s1 +idx_s2 +1]

                if c1 == c3:
                    r1 = dp[idx_row -1][idx_col]

                if c2 == c3:
                    r2 = dp[idx_row][idx_col -1]

                dp[idx_row][idx_col] = r1 or r2

        return dp[len(s1)][len(s2)]
