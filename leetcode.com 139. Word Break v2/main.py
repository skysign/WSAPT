from typing import List


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        dp: List[bool] = [False for _ in range(len(s))]
        l = len(s)
        bgns: List[int] = [0]

        while len(bgns) > 0:
            bgn = bgns.pop(0)

            for word in wordDict:
                if s[bgn:].startswith(word):
                    bgn_new = bgn + len(word)
                    if bgn_new == l:
                        return True
                    else:
                        if not bgn_new in bgns and dp[bgn_new] == False:
                            bgns.append(bgn_new)
                            dp[bgn_new] = True

        return False
