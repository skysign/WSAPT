from typing import List


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        dp = [False for _ in range(len(s) + 1)]
        dp[0] = True

        return self.myWordBreak(s, 0, len(s), wordDict, dp)

    def myWordBreak(self, s: str, fr, length, wordDict: List[str], dp: List[bool]):
        if fr == length:
            return True

        for word in wordDict:
            if fr + len(word) <= length and dp[fr + len(word)] == False and s[fr: fr + len(word)] == word:
                dp[fr + len(word)] = True
                if self.myWordBreak(s, fr+len(word), length, wordDict, dp):
                    return True

        return False
