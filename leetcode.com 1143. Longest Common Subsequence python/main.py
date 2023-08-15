class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        txt_long = ' '
        txt_short = ' '

        if len(text1) > len(text2):
            txt_long += text1
            txt_short += text2
        else:
            txt_long += text2
            txt_short += text1

        dp = [[0 for _ in range(len(txt_long))] for _ in range(len(txt_short)) ]

        # dp[i][j]의 의미
        # txt_long[1:i+1] 스트링과 txt_short와 LCS값

        for idx_row in range(1, len(txt_short)):
            for idx_col in range(1, len(txt_long)):
                char_long = txt_long[idx_col]
                char_short = txt_short[idx_row]

                if char_long == char_short:
                    dp[idx_row][idx_col] = dp[idx_row -1][idx_col -1] +1
                else:
                    dp[idx_row][idx_col] = max(dp[idx_row -1][idx_col], dp[idx_row][idx_col -1])

        return dp[idx_row][len(txt_long) -1]