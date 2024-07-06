from typing import List


class Solution:
    def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
        if len(s3) != (len(s1) + len(s2)):
            return False

        s1 = ' ' + s1
        s2 = ' ' + s2
        s3 = ' ' + s3

        dp: List[List[bool]] = [[False for _ in range(len(s1))] for _ in range(len(s2))]
        dp[0][0] = True

        for i2 in range(0, len(s2)):
            for i1 in range(0, len(s1)):
                if i1 - 1 >= 0 and dp[i2][i1 - 1]:
                    if s3[i2 + i1] == s1[i1]:
                        dp[i2][i1] = True

                if i2 - 1 >= 0 and dp[i2 - 1][i1]:
                    if s3[i2 + i1] == s2[i2]:
                        dp[i2][i1] = True

        return dp[len(s2) - 1][len(s1) - 1]

    # def isInterleave(self, s1: str, s2: str, s3: str) -> bool:
    #     if len(s3) != (len(s1) + len(s2)):
    #         return False
    #
    #     if len(s3) == 0 and len(s1) == 0 and len(s2) == 0:
    #         return True
    #
    #     if len(s1) > 0:
    #         if s1[0] == s3[0]:
    #             if self.isInterleave(s1[1:], s2, s3[1:]):
    #                 return True
    #
    #     if len(s2) > 0:
    #         if s2[0] == s3[0]:
    #             if self.isInterleave(s1, s2[1:], s3[1:]):
    #                 return True
    #
    #     return False
