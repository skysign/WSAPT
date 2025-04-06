class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        w1, w2 = ' ' + word1, ' ' + word2
        dp = [[0 for _ in range(len(w2))] for _ in range(len(w1))]

        for row in range(len(w1)):
            dp[row][0] = row

        for col in range(len(w2)):
            dp[0][col] = col

        for row in range(1, len(w1)):
            for col in range(1, len(w2)):
                if w1[row] == w2[col]:
                    dp[row][col] = dp[row - 1][col - 1]
                else:
                    dp[row][col] = min(dp[row - 1][col - 1], dp[row][col - 1], dp[row - 1][col]) + 1

        return dp[-1][-1]
