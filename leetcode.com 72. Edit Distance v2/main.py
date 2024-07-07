from typing import List


class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        word1 = ' ' + word1
        word2 = ' ' + word2
        dp: [List[List[int]]] = [[0 for _ in range(len(word1))] for _ in range(len(word2))]

        for i in range(len(word1)):
            dp[0][i] = i

        for i in range(len(word2)):
            dp[i][0] = i

        for i2 in range(1, len(word2)):
            for i1 in range(1, len(word1)):
                if word2[i2] == word1[i1]:
                    dp[i2][i1] = dp[i2 - 1][i1 - 1]
                else:
                    dp[i2][i1] = min(dp[i2 - 1][i1 - 1], dp[i2][i1 - 1], dp[i2 - 1][i1]) + 1

        return dp[len(word2) - 1][len(word1) - 1]