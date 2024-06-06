class Solution:
    def longestPalindrome(self, s: str) -> str:
        answer = ''
        length = len(s)

        for idx in range(length):
            local_answer = self.expand(idx, idx, s, length)
            answer = local_answer if len(local_answer) > len(answer) else answer

            if idx < length - 1:
                local_answer = self.expand(idx, idx + 1, s, length)
                answer = local_answer if len(local_answer) > len(answer) else answer

        return answer

    def expand(self, idx_left, idx_right, s, length) -> str:
        rtn: str = ''

        while 0 <= idx_left < length and 0 <= idx_right < length:
            if s[idx_left] == s[idx_right]:
                rtn = s[idx_left:idx_right + 1]
                idx_left -= 1
                idx_right += 1
            else:
                break

        return rtn
