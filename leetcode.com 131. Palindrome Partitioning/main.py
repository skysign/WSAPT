from typing import List


class Solution:
    def partition(self, s: str) -> List[List[str]]:
        answer = []
        self.bt(s, 0, [], answer)

        return answer

    def bt(self, rest, idx_left, pathes, answer):
        if len(rest) == 0:
            answer.append(pathes)
            return

        for idx_right in range(idx_left, len(rest)):
            part = rest[idx_left:idx_right + 1]
            if self.is_palindrome(part):
                self.bt(rest[idx_right + 1:], 0, pathes + [part], answer)

    def is_palindrome(self, s):
        idx_left, idx_right = 0, len(s) - 1

        while (idx_left <= idx_right):
            if s[idx_left] == s[idx_right]:
                idx_left += 1
                idx_right -= 1
                continue
            else:
                return False

        return True
