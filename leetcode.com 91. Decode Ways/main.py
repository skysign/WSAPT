from typing import List


class Solution:
    def numDecodings(self, s: str) -> int:
        if s[0] == '0':
            return 0

        n = len(s)
        dp: List[int] = [0] * (n + 1)
        dp[0] = 1
        dp[1] = 1

        for i in range(2, n + 1):
            onedigit = int(s[i - 1])
            twodigit = int(s[i - 2:i])

            if 1 <= onedigit <= 9:
                dp[i] += dp[i - 1]
            if 10 <= twodigit <= 26:
                dp[i] += dp[i - 2]

        return dp[n]
