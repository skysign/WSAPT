class Solution:
    def countSubstrings(self, s: str) -> int:
        answer = 0
        length = len(s)

        for i in range(length):
            answer += self.expand(i, i, s, length)

            if i + 1 < length:
                answer += self.expand(i, i + 1, s, length)

        return answer

    def expand(self, idx_left, idx_right, s, length):
        rtn = 0

        while 0 <= idx_left < length and 0 <= idx_right < length:
            if s[idx_left] == s[idx_right]:
                rtn += 1
                idx_left -= 1
                idx_right += 1
            else:
                break

        return rtn
