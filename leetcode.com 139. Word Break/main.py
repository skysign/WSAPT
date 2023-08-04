import copy

class Solution:
    answer = False

    def wordBreak(self, s: str, wordDict: list[str]):
        dt = s
        # dp 배열에 True로 assign된다는 의미, 단어의 첫번째 글자
        # 따라서, 현재 segment한 단어의 마지막 글자의 index +1에 True가 assigne됨
        # 결과적으로, dt의 길이보다, dp의 길아가 1 커야 한다.
        dp = [False for _ in range(len(dt) +1)]

        dp[0] = True

        words = wordDict

        for idxTo in range(len(dt)):
            for word in words:
                idxFrom = idxTo - len(word) +1
                if idxFrom >= 0:
                    if dp[idxFrom]:
                        if dt[idxFrom: idxFrom +len(word)] == word:
                            dp[idxTo+1] = True

        return dp[len(dp) -1]
