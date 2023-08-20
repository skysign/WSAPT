class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        answer = 0
        dp = [[0 for _ in range(len(' ' +word1))] for _ in range(len(' ' +word2))]

        for idxrow in range(1, len(' ' + word2)):
            dp[idxrow][0] = idxrow

        for idxcol in range(1, len(' ' + word1)):
            dp[0][idxcol] = idxcol

        for idxrow in range(1, len(' ' +word2)):
            idx2 = idxrow -1

            for idxcol in range(1, len(' ' +word1)):
                idx1 = idxcol - 1

                if word1[idx1] == word2[idx2]:
                    dp[idxrow][idxcol] = dp[idxrow - 1][idxcol - 1]
                else:
                    dp[idxrow][idxcol] = min(dp[idxrow -1][idxcol], dp[idxrow][idxcol - 1], dp[idxrow - 1][idxcol - 1]) +1

        return dp[len(word2)][len(word1)]

