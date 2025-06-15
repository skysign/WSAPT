from collections import deque


class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        if len(s3) != (len(s1) + len(s2)):
            return False

        s1, s2, s3 = ' ' + s1, ' ' + s2, ' ' + s3

        dp = [[False for _ in range(len(s2))] for _ in range(len(s1))]
        dp[0][0] = True

        for i1 in range(0, len(s1)):
            for i2 in range(0, len(s2)):
                if i2 - 1 >= 0 and dp[i1][i2 - 1]:
                    if s3[i1 + i2] == s2[i2]:
                        dp[i1][i2] = True

                if i1 - 1 >= 0 and dp[i1 - 1][i2]:
                    if s3[i1 + i2] == s1[i1]:
                        dp[i1][i2] = True

        return dp[len(s1) - 1][len(s2) - 1]
